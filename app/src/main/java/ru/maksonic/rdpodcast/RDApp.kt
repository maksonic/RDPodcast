package ru.maksonic.rdpodcast

import android.app.Application
import androidx.emoji2.bundled.BundledEmojiCompatConfig
import androidx.emoji2.text.EmojiCompat
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @Author: maksonic on 06.02.2022
 */
@HiltAndroidApp
class RDApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setEmojiSupport()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setEmojiSupport() {
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)
    }
}