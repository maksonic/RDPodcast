package ru.maksonic.rdpodcast.feature.podcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.feature.podcast.databinding.ScreenFavoritesBinding

/**
 * @Author: maksonic on 08.02.2022
 */
class FavoritesScreen : BaseFragment<ScreenFavoritesBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenFavoritesBinding
        get() = ScreenFavoritesBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
    }
}