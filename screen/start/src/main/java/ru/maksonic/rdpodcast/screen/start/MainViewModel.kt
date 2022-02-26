package ru.maksonic.rdpodcast.screen.start

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * @Author: maksonic on 06.02.2022
 */

data class Authorized(val isUserLoggedIn: Boolean = false)

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(Authorized())
    val uiState: StateFlow<Authorized> = _uiState.asStateFlow()

    private val _uiPodcastName = MutableStateFlow("")
    val uiPodcastName: StateFlow<String> = _uiPodcastName.asStateFlow()

    init {
        setPodcastName(uiPodcastName.value)
        _uiState.value = Authorized(false)
    }

    fun setPodcastName(name: String) {
        _uiPodcastName.value = name
    }

    fun userAuthState(navController: NavController) {
        if (!_uiState.value.isUserLoggedIn) {
            if (navController.currentDestination?.id == R.id.onboardingScreen) {
                return
            }
            else {
                navController.navigate(R.id.onboardingScreen)
            }
        }
    }
}