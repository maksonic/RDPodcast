package ru.maksonic.rdpodcast.screen.categories

import ru.maksonic.rdpodcast.core.base.presentation.architecture.ScreenState
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi

/**
 * @Author: maksonic on 07.02.2022
 */
object CategoriesFeature {

    interface Render {
        fun initRecyclerAdapter()
        fun initSwipeRefreshLayout()
        fun render(state: State)
        fun renderLoadingState(state: State.Loading)
        fun renderFetchedState(state: State.Fetched)
        fun renderRefreshState(state: State.Refresh)
        fun renderRefreshedState(state: State.Refreshed)
        fun renderErrorState(state: State.Error)
    }

    sealed class State : ScreenState {
        object Loading : State()
        data class Fetched(val fetchedCategories: List<CategoryUi> = emptyList()) : State()
        object Refresh : State()
        data class Refreshed(val refreshedCategories: List<CategoryUi> = emptyList()) : State()
        data class Error(val message: String? = null) : State()
    }

    sealed class Msg {
        sealed class Ui : Msg() {
            object FetchCategories : Ui()
            object RefreshCategories : Ui()
            data class OnCategoryClicked(val navigator: Navigator, val category: CategoryUi?) : Ui()
        }

        sealed class Internal : Msg() {
            data class FetchingResult(val categories: List<CategoryUi>) : Internal()
            data class RefreshingResult(val refreshed: List<CategoryUi>) : Internal()
            data class ShowError(val message: String) : Internal()
        }
    }

    sealed class Cmd {
        object FetchCacheOrCloudCategories : Cmd()
        object FetchCloudCategories : Cmd()
        data class NavigateToPodcastList(
            val navigator: Navigator, val category: CategoryUi?
        ) : Cmd()
    }
}