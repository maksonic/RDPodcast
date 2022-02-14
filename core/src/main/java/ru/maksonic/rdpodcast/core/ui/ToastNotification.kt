package ru.maksonic.rdpodcast.core.ui

import android.content.Context
import android.widget.Toast

/**
 * @Author: maksonic on 14.02.2022
 */

fun toastShort(context: Context?, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
fun toastLong(context: Context?, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}