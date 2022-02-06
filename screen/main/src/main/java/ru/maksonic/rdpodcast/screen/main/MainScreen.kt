package ru.maksonic.rdpodcast.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.screen.main.databinding.ScreenMainBinding

/**
 * @Author: maksonic on 06.02.2022
 */
class MainScreen : BaseFragment<ScreenMainBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenMainBinding =
        ScreenMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
    }
}