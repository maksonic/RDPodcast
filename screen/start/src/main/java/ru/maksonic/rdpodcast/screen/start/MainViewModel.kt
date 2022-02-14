package ru.maksonic.rdpodcast.screen.start

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @Author: maksonic on 06.02.2022
 */

data class Authorized(val isUserLoggedIn: Boolean = false)

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(Authorized())
    val uiState: StateFlow<Authorized> = _uiState.asStateFlow()

    init {
        _uiState.value = Authorized(true)
    }


    fun userAuthState(navController: NavController) {
        if (_uiState.value.isUserLoggedIn) {
            navController.navigate(R.id.mainScreen)
        } else {
            if (navController.currentDestination?.id == R.id.onboardingScreen) {
                return
            }
            else {
                navController.navigate(R.id.onboardingScreen)
            }
        }
    }
}