package ru.maksonic.rdpodcast.feature.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.ToolBarBehavior
import ru.maksonic.rdpodcast.feature.user_profile.databinding.ScreenProfileBinding

/**
 * @Author: maksonic on 09.03.2022
 */
class ProfileScreen : BaseFragment<ScreenProfileBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenProfileBinding
        get() = ScreenProfileBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.toolBarProfile.initToolbar(getString(R.string.scr_title_user_profile), navIcon = R.drawable.ic_close, this)
    }
}