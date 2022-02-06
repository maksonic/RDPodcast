package ru.maksonic.rdpodcast.navigation.api

import androidx.fragment.app.Fragment
import javax.inject.Inject

/**
 * @Author: maksonic on 06.02.2022
 */
interface Router {
    fun showOnboarding()
}

class Navigator @Inject constructor(val fragment: Fragment) : Router {
    override fun showOnboarding() {
        fragment.navigate(R.id.action_mainScreen_to_onboarding_graph)
    }
}