package ru.maksonic.rdpodcast.screen.categories

import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi

/**
 * @Author: maksonic on 07.02.2022
 */
object CategoriesFeature {

    interface Render {
        fun initRecyclerAdapter()
        fun render(state: State)
        fun renderLoadingState(state: State.Loading)
        fun renderFetchedState(state: State.Fetched)
        fun renderRefreshState(state: State.Refresh)
        fun renderRefreshedState(state: State.Refreshed)
        fun renderErrorState(state: State.Error)
    }

    sealed class State {
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
            object OnCategoryClicked : Ui()
        }

        sealed class Internal : Msg() {
            data class FetchingResult(val categories: List<CategoryUi>) : Msg()
            data class RefreshingResult(val refreshed: List<CategoryUi>) : Msg()
            data class ShowError(val message: String) : Msg()
        }
    }

    sealed class Cmd {
        object FetchCacheOrCloudCategories : Cmd()
        object FetchCloudCategories : Cmd()
        object NavigateToPodcastList : Cmd()
    }
}