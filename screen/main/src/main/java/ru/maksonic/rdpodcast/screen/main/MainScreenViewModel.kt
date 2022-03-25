package ru.maksonic.rdpodcast.screen.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.maksonic.rdpodcast.domain.podcast.PodcastInteractor
import javax.inject.Inject

/**
 * @Author: maksonic on 23.03.2022
 */
@HiltViewModel
class MainScreenViewModel @Inject constructor(val podcastInteractor: PodcastInteractor): ViewModel() {

   /* private val _uiState = MutableStateFlow(Authorized())
    val uiState: StateFlow<Authorized> = _uiState.asStateFlow()*/

    private val _uiPodcastName = MutableStateFlow("")
    val uiPodcastName: StateFlow<String> = _uiPodcastName.asStateFlow()

    init {
        setPodcastName(uiPodcastName.value)
      //  _uiState.value = Authorized(false)
    }

    fun setPodcastName(name: String) {
        _uiPodcastName.value = name
    }

    suspend fun currentPodcast() = podcastInteractor.getCurrentPodcastUi()

}