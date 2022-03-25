package ru.maksonic.rdpodcast.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.screen.home.databinding.ScreenHomeBinding

/**
 * @Author: maksonic on 23.03.2022
 */
class HomeScreen : BaseFragment<ScreenHomeBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenHomeBinding
        get() = ScreenHomeBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

    }
}