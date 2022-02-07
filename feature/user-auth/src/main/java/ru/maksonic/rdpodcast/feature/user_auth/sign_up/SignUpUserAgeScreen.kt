package ru.maksonic.rdpodcast.feature.user_auth.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.click
import ru.maksonic.rdpodcast.feature.user_auth.R
import ru.maksonic.rdpodcast.feature.user_auth.databinding.ScreenSignUpUserAgeBinding
import ru.maksonic.rdpodcast.navigation.api.Navigator

/**
 * @Author: maksonic on 12.01.2022
 */
class SignUpUserAgeScreen : BaseFragment<ScreenSignUpUserAgeBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenSignUpUserAgeBinding
        get() = ScreenSignUpUserAgeBinding::inflate

    private var _navigator: Navigator? = null
    private val navigator: Navigator
        get() = _navigator!!

    override fun prepareView(savedInstanceState: Bundle?) {
        _navigator = Navigator(this)
      //  binding.toolBar.initToolbar(getString(R.string.toolbar_scr_sign_up_user_age),
         //   fragment = this)
        binding.btnNext.click {
        //    navigator.signUpUserAgeToPhone()
        }
    }
}