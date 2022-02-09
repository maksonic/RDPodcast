package ru.maksonic.rdpodcast.feature.podcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.feature.podcast.databinding.ScreenDownloadsBinding

/**
 * @Author: maksonic on 08.02.2022
 */
class DownloadsScreen : BaseFragment<ScreenDownloadsBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenDownloadsBinding
        get() = ScreenDownloadsBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
    }
}