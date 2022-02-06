package ru.maksonic.rdpodcast.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.screen.main.databinding.ScreenMainBinding
import javax.inject.Inject

/**
 * @Author: maksonic on 06.02.2022
 */
@AndroidEntryPoint
class MainScreen : BaseFragment<ScreenMainBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenMainBinding =
        ScreenMainBinding::inflate
    @Inject
    lateinit var navigator: Navigator
    override fun prepareView(savedInstanceState: Bundle?) {
        navigator = Navigator(this)

        navigator.showOnboarding()
    }
}