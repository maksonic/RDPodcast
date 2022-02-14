package ru.maksonic.rdpodcast.core.ui

import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * @Author: maksonic on 14.02.2022
 */
fun BottomSheetBehavior<*>.setStateCollapsed() {
    this.state = BottomSheetBehavior.STATE_COLLAPSED
}

fun BottomSheetBehavior<*>.setStateHidden() {
    this.state = BottomSheetBehavior.STATE_HIDDEN
}

fun BottomSheetBehavior<*>.setStateExpanded() {
    this.state = BottomSheetBehavior.STATE_EXPANDED
}