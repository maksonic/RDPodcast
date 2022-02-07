package ru.maksonic.rdpodcast.feature.user_auth.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.rdpodcast.core.base.presentation.BaseFragment
import ru.maksonic.rdpodcast.core.ui.click
import ru.maksonic.rdpodcast.feature.user_auth.databinding.ScreenSignUpPhoneBinding
import ru.maksonic.rdpodcast.navigation.api.Navigator

/**
 * @Author: maksonic on 06.02.2022
 */
class SignUpPhoneScreen : BaseFragment<ScreenSignUpPhoneBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> ScreenSignUpPhoneBinding
        get() = ScreenSignUpPhoneBinding::inflate
    private lateinit var navigator: Navigator

    override fun prepareView(savedInstanceState: Bundle?) {
        navigator = Navigator(this)
  //      binding.toolBar.initToolbar(getString(R.string.toolbar_scr_sign_up_phone),
        //    fragment = this)
        binding.btnNext.click {
      //      navigator.signUpToConfirmPhone()
        }
    }
    override fun onResume() {
        super.onResume()
      //  showKeyboard(binding.inputPhoneNumber)
    }

    override fun onStop() {
        super.onStop()
      //  hideKeyboard(binding.inputPhoneNumber)
    }
}