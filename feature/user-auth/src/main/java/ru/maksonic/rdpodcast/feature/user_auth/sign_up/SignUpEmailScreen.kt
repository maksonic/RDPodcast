package ru.maksonic.rdpodcast.feature.user_auth.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.feature.user_auth.databinding.ScreenSignUpEmailBinding

/**
 * @Author: maksonic on 06.02.2022
 */
class SignUpEmailScreen : BaseFragment<ScreenSignUpEmailBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenSignUpEmailBinding
        get() = ScreenSignUpEmailBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
    }
}