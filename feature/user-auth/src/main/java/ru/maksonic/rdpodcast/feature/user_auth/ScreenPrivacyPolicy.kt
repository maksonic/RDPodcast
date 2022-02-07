package ru.maksonic.rdpodcast.feature.user_auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.rdpodcast.core.base.presentation.BaseFullScreenBottomSheetDialog
import ru.maksonic.rdpodcast.feature.user_auth.databinding.ScreenPrivacyBinding
import ru.maksonic.rdpodcast.navigation.api.navigationData

/**
 * @Author: maksonic on 06.02.2022
 */
@AndroidEntryPoint
class ScreenPrivacyPolicy : BaseFullScreenBottomSheetDialog<ScreenPrivacyBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenPrivacyBinding
        get() = ScreenPrivacyBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        val data = navigationData as? PrivacyPolicy ?: return
        setScreenContent(data.content)
    }

    private fun setScreenContent(data: String) = with(binding) { content.text = data }
}

