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
        fun initRecyclerAdapter()
        fun initSwipeRefreshLayout(category: String?)
        fun render(state: State)
        fun renderLoadingState(state: State.Loading)
        fun renderFetchedState(state: State.Fetched)
        fun renderRefreshState(state: State.Refresh)
        fun renderRefreshedState(state: State.Refreshed)
        fun renderErrorState(state: State.Error)
    }


    sealed class State {
        object Loading : State()
        data class Fetched(val fetchedPodcasts: List<PodcastUi> = emptyList()) : State()
        object Refresh : State()
        data class Refreshed(val refreshedCategories: List<PodcastUi> = emptyList()) : State()
        data class Error(val message: String? = null) : State()
        data class ScreenHeader(val categoryName: String? = "",val categoryImg: String? = ""): State()
    }

    sealed class Msg {
        sealed class Ui : Msg() {
            data class FetchPodcasts(val category: String?) : Ui()
            data class RefreshPodcasts(val category: String?) : Ui()
            data class InitScreenHeader(val fragment: Fragment, val categoryName: String? = "",  val categoryImg: String? = "") : Ui()
            //   object OnPodcastClicked : Ui()
            // object OnLongPodcastClicked : Ui()
        }

        sealed class Internal : Msg() {
            data class FetchingResult(val podcasts: List<PodcastUi>) : Msg()
            data class RefreshingResult(val refreshed: List<PodcastUi>) : Msg()
            data class ScreenHeaderResult(val fragment: Fragment, val categoryName: String? = "",  val categoryImg: String? = "") : Msg()
            data class ShowError(val message: String) : Msg()
        }
    }

    sealed class Cmd {
        data class OnFetchCloudPodcast(val category: String?) : Cmd()
        data class OnInitScreenHeader(val fragment: Fragment) : Cmd()
    }
}