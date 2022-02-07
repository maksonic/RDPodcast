package ru.maksonic.rdpodcast.core.base.presentation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.maksonic.rdpodcast.core.R

/**
 * @Author: maksonic on 06.02.2022
 */
abstract class BaseBottomSheetDialog<VB : ViewBinding> : BottomSheetDialogFragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            initBottomSheetDialog()
        }
        return dialog
    }

    private fun initBottomSheetDialog() {
        val bottomSheet = with(binding.root.rootView) {
            findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        }

        bottomSheet.background = requireContext().let {
            ResourcesCompat.getDrawable(resources,
                R.drawable.bg_bottom_sheet,
                requireContext().theme)
        }
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet).apply {
            state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindLayout.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView(savedInstanceState)
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialog.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}