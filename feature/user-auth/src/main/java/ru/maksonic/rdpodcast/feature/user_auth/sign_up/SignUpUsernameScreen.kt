package ru.maksonic.rdpodcast.feature.user_auth.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.click
import ru.maksonic.rdpodcast.feature.user_auth.R
import ru.maksonic.rdpodcast.feature.user_auth.databinding.ScreenSignUpUsernameBinding
import ru.maksonic.rdpodcast.navigation.api.Navigator

/**
 * @Author: maksonic on 12.01.2022
 */
class SignUpUsernameScreen : BaseFragment<ScreenSignUpUsernameBinding>() {

    private lateinit var navigator: Navigator

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenSignUpUsernameBinding
        get() = ScreenSignUpUsernameBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        navigator = Navigator(this)
        binding.toolBar.initToolbar(getString(R.string.toolbar_scr_sign_up_username), fragment = this)
        binding.btnNext.click {
     //       navigator.signUpUsernameToUserAge()
        }
    }

    override fun onResume() {
        super.onResume()
        //showKeyboard(binding.inputUsername)
    }

    override fun onStop() {
        super.onStop()
      //  hideKeyboard(binding.inputUsername)
    }

}