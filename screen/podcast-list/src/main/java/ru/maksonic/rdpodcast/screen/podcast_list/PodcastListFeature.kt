package ru.maksonic.rdpodcast.screen.podcast_list

import androidx.fragment.app.Fragment
import ru.maksonic.rdpodcast.feature.podcast.PodcastAdapter
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi
import ru.maksonic.rdpodcast.shared.ui_model.PodcastUi

/**
 * @Author: maksonic on 10.02.2022
 */
object PodcastListFeature {

    interface Render {
        fun initHeader()
        fun initRecyclerAdapter()
        fun initSwipeRefreshLayout(category: String?)

        fun render(state: State)
        fun renderLoadingState(state: State.Loading)
        fun renderFetchedState(state: State.Fetched)
        fun renderRefreshState(state: State.Refresh)
        fun renderRefreshedState(state: State.Refreshed)
        fun renderErrorState(state: State.Error)

        fun fetchDataAction()
        fun refreshDataAction(category: String?)
    }


    sealed class State {
        object Loading : State()

        data class Fetched(
            val fetchedPodcasts: List<PodcastUi> = emptyList(),
            val podcastCount: Int
        ) : State()

        object Refresh : State()
        data class Refreshed(
            val refreshedCategories: List<PodcastUi> = emptyList(),
            val podcastCount: Int
        ) : State()

        data class Error(val message: String? = null) : State()
    }

    sealed class Msg {
        sealed class Ui : Msg() {
            data class FetchPodcasts(val category: String?, val podcastCount: Int = 0) : Ui()
            data class RefreshPodcasts(val category: String?, val podcastCount: Int = 0) : Ui()
        }

        sealed class Internal : Msg() {
            data class FetchingResult(
                val podcasts: List<PodcastUi>, val podcastCount: Int
            ) : Internal()

            data class RefreshingResult(
                val refreshed: List<PodcastUi>, val podcastCount: Int
            ) : Internal()

            data class ShowError(val message: String) : Internal()
        }
    }

    sealed class Cmd {
        data class OnFetchCloudPodcast(val category: String?) : Cmd()
    }
}