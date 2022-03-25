package ru.maksonic.rdpodcast.screen.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.ToolBarBehavior
import ru.maksonic.rdpodcast.screen.settings.databinding.ScreenSettingsBinding

/**
 * @Author: maksonic on 09.03.2022
 */
class SettingsScreen : BaseFragment<ScreenSettingsBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenSettingsBinding
        get() = ScreenSettingsBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.toolBarSettings.initToolbar(getString(R.string.scr_title_settings), navIcon = R.drawable.ic_close, this)
    }
}