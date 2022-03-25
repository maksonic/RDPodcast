package ru.maksonic.rdpodcast.core

import android.content.Context
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject


/**
 * @Author: maksonic on 07.02.2022
 */
interface ResourceProvider {
    fun getString(@StringRes id: Int, vararg formatArgs: Any?): String
    val Context.datastore: DataStore<Preferences>

    class Base @Inject constructor(private val context: Context) : ResourceProvider {
        private companion object {
            private const val DS_NAME = "rd_datastore"
        }

        override fun getString(id: Int, vararg formatArgs: Any?) =
            context.getString(id, *formatArgs)

        override val Context.datastore by preferencesDataStore(name = DS_NAME)
    }
}
