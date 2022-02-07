package ru.maksonic.rdpodcast.core.base.presentation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * @Author: maksonic on 06.02.2022
 */
abstract class BaseFullScreenBottomSheetDialog<VB : ViewBinding> : BottomSheetDialogFragment() {

    lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onStart() {
        super.onStart()
        setFullScreenDialog(dialog as BottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        // Value -1 means disable like bottom sheet animation
        dialog.window?.setWindowAnimations(-1)
        return dialog
    }

    private fun setFullScreenDialog(dialog: BottomSheetDialog) {
        val dialogHeight = resources.displayMetrics.heightPixels
        dialog.let {
            val bottomSheet =
                it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            bottomSheet.layoutParams.height = dialogHeight
            BottomSheetBehavior.from(bottomSheet).apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                peekHeight = dialogHeight
            }
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

    protected fun initToolbar(toolbar: Toolbar, tbTitle: String) {
        toolbar.apply {
            title = tbTitle
            setNavigationOnClickListener { onDismiss(requireDialog()) }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialog.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}