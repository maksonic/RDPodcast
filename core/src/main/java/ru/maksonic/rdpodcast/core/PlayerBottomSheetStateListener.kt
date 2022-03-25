package ru.maksonic.rdpodcast.core

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.maksonic.rdpodcast.core.ui.setStateCollapsed
import ru.maksonic.rdpodcast.core.ui.setStateExpanded
import javax.inject.Inject

/**
 * @Author: maksonic on 24.03.2022
 */
typealias PlayerSheetStateListener = PlayerBottomSheetStateListener.Base
interface PlayerBottomSheetStateListener {
    fun listenState(bottomSheetBehavior: BottomSheetBehavior<*>)
    fun setExpandState()
    fun setCollapseState()

    class Base @Inject constructor(val fragment: Fragment) : PlayerBottomSheetStateListener {
        private companion object {
            const val REQUEST_KEY = "bottom_sheet_state_listener"
            const val BUNDLE_KEY = "bottom_sheet_state_key"
            const val EXPAND = "bottom_sheet_state_expanded"
            const val COLLAPSE = "bottom_sheet_state_collapsed"
        }

        override fun listenState(bottomSheetBehavior: BottomSheetBehavior<*>) {
            fragment.requireActivity().supportFragmentManager.setFragmentResultListener(
                REQUEST_KEY,
                fragment.viewLifecycleOwner
            ) { _, bundle ->
                val result = bundle.getString(BUNDLE_KEY)
                if (result == COLLAPSE) {
                    bottomSheetBehavior.setStateCollapsed()
                }
                if (result == EXPAND) {
                    bottomSheetBehavior.setStateExpanded()
                }
            }
        }

        override fun setCollapseState() {
            fragment.requireActivity().supportFragmentManager.setFragmentResult(
                REQUEST_KEY, bundleOf(
                    BUNDLE_KEY to COLLAPSE
                )
            )
        }

        override fun setExpandState() {
            fragment.requireActivity().supportFragmentManager.setFragmentResult(
                REQUEST_KEY, bundleOf(
                    BUNDLE_KEY to EXPAND
                )
            )
        }
    }
}