package ru.maksonic.rdpodcast.core

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject


/**
 * @Author: maksonic on 07.02.2022
 */
interface ResourceProvider {
    fun getString(@StringRes id: Int, vararg formatArgs: Any?): String

    class Base @Inject constructor(private val context: Context) : ResourceProvider {
        override fun getString(id: Int, vararg formatArgs: Any?) =
            context.getString(id, *formatArgs)
    }
}
