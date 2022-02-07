package ru.maksonic.rdpodcast.core.ui

import android.os.SystemClock
import android.view.View

/**
 * @Author: maksonic on 05.02.2022
 */
abstract class DebounceClickListener : View.OnClickListener {
    private val debounceTime: Int = 600
    private var defaultInterval: Int = debounceTime
    private var lastTimeClicked: Long = 0

    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        debounceClick(v)
    }
    abstract fun debounceClick(v: View?)
}

fun View.click(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}
