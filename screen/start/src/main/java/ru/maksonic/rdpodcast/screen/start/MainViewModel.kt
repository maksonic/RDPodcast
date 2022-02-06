package ru.maksonic.rdpodcast.screen.start

import androidx.lifecycle.ViewModel

/**
 * @Author: maksonic on 06.02.2022
 */
class MainViewModel: ViewModel() {

    private val user = 0

    fun validateUser(): Int {
        val onboardingScreen = R.id.onboardingScreen
        val mainScreen = R.id.mainScreen
        return if (user != 0) mainScreen else onboardingScreen
    }
}