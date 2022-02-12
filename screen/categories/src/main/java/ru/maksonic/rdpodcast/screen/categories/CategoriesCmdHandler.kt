package ru.maksonic.rdpodcast.screen.categories

import ru.maksonic.rdpodcast.core.base.presentation.architecture.CommandHandler
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.domain.categories.CategoriesInteractor
import ru.maksonic.rdpodcast.navigation.api.Navigator
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoriesCmdHandler @Inject constructor(
    private val interactor: CategoriesInteractor,
    private val mapper: CategoryDomainToUiMapper,
) : CommandHandler<CategoriesFeature.Cmd, CategoriesFeature.Msg> {

    override suspend fun execute(
        cmd: CategoriesFeature.Cmd,
        consumer: (CategoriesFeature.Msg) -> Unit,
    ) {
        when (cmd) {
            is CategoriesFeature.Cmd.FetchCacheOrCloudCategories -> fetchCategories(consumer)
            is CategoriesFeature.Cmd.FetchCloudCategories -> refreshCategories(consumer)
            is CategoriesFeature.Cmd.NavigateToPodcastList -> {
                navigateToPodcastList(cmd.navigator, cmd.category)
            }
        }
    }

    private suspend fun fetchCategories(consumer: (CategoriesFeature.Msg) -> Unit) {
        consumer(
            when (val result = interactor.fetchCategories()) {
                is Result.Success -> {
                    val categories = mapper.fromList(result.data)
                    CategoriesFeature.Msg.Internal.FetchingResult(categories)
                }
                is Result.Error -> CategoriesFeature.Msg.Internal.ShowError(result.exception)
            }
        )
    }

    private suspend fun refreshCategories(consumer: (CategoriesFeature.Msg) -> Unit) {
        consumer(
            when (val result = interactor.refreshCategories()) {
                is Result.Success -> {
                    val categories = mapper.fromList(result.data)
                    CategoriesFeature.Msg.Internal.RefreshingResult(categories)
                }
                is Result.Error -> CategoriesFeature.Msg.Internal.ShowError(result.exception)
            }
        )
    }

    private fun navigateToPodcastList(navigator: Navigator, category: CategoryUi?) {
        navigator.toCategoryPodcastList(category)
    }
}