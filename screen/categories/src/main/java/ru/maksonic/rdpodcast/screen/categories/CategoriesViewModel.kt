package ru.maksonic.rdpodcast.screen.categories

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdpodcast.core.base.presentation.architecture.FeatureHolder
import ru.maksonic.rdpodcast.domain.CategoriesInteractor
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
private typealias UpdateResult = Pair<CategoriesFeature.State, Set<CategoriesFeature.Cmd>>

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    categoriesInteractor: CategoriesInteractor,
    mapper: CategoryDomainToUiMapper,
) : FeatureHolder<CategoriesFeature.Msg, CategoriesFeature.State, CategoriesFeature.Cmd>(
    initState = CategoriesFeature.State.Loading,
    initCommands = setOf(CategoriesFeature.Cmd.FetchCacheOrCloudCategories),
    CategoriesFeature.Cmd::class to CategoriesCmdHandler(
        categoriesInteractor,
        mapper,
    )
) {
    override fun update(
        msg: CategoriesFeature.Msg,
        state: CategoriesFeature.State,
    ): UpdateResult =
        when (msg) {
            is CategoriesFeature.Msg.Ui.FetchCategories -> {
                CategoriesFeature.State
                    .Loading to setOf(CategoriesFeature.Cmd.FetchCacheOrCloudCategories)
            }
            is CategoriesFeature.Msg.Ui.RefreshCategories -> {
                CategoriesFeature.State
                    .Refresh to setOf(CategoriesFeature.Cmd.FetchCloudCategories)
            }
            is CategoriesFeature.Msg.Ui.OnCategoryClicked -> {
                state to setOf(CategoriesFeature.Cmd.NavigateToPodcastList)
            }
            is CategoriesFeature.Msg.Internal.RefreshingResult -> {
                CategoriesFeature.State.Refreshed(refreshedCategories = msg.refreshed) to emptySet()
            }
            is CategoriesFeature.Msg.Internal.FetchingResult -> {
                CategoriesFeature.State.Fetched(fetchedCategories = msg.categories) to emptySet()
            }
            is CategoriesFeature.Msg.Internal.ShowError -> {
                CategoriesFeature.State.Error(message = msg.message) to emptySet()
            }
        }
}