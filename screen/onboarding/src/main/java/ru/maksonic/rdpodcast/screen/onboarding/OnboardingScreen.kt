package ru.maksonic.rdpodcast.screen.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.BaseFragment
import ru.maksonic.rdpodcast.screen.onboarding.databinding.ScreenOnboardingBinding

/**
 * @Author: maksonic on 05.02.2022
 */
class OnboardingScreen : BaseFragment<ScreenOnboardingBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenOnboardingBinding
        get() = ScreenOnboardingBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

    }
}