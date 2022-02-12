package ru.maksonic.rdpodcast.screen.podcast_list

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdpodcast.core.base.presentation.architecture.FeatureHolder
import ru.maksonic.rdpodcast.domain.podcast.PodcastInteractor
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
typealias UpdateResult = Pair<PodcastListFeature.State, Set<PodcastListFeature.Cmd>>

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    interactor: PodcastInteractor,
    mapper: PodcastDomainToUiMapper,
) : FeatureHolder<PodcastListFeature.Msg, PodcastListFeature.State, PodcastListFeature.Cmd>(
    initState = PodcastListFeature.State.Loading,
    initCommands = emptySet(),
    PodcastListFeature.Cmd::class to PodcastsCmdHandler(
        interactor,
        mapper,
    )
) {
    override fun update(
        msg: PodcastListFeature.Msg,
        state: PodcastListFeature.State
    ): UpdateResult = when (msg) {
        is PodcastListFeature.Msg.Ui.FetchPodcasts -> PodcastListFeature.State.Loading to setOf(
            PodcastListFeature.Cmd.OnFetchCloudPodcast(msg.category)
        )
        is PodcastListFeature.Msg.Ui.RefreshPodcasts -> PodcastListFeature.State.Refresh to setOf(
            PodcastListFeature.Cmd.OnFetchCloudPodcast(msg.category)
        )
        is PodcastListFeature.Msg.Internal.FetchingResult -> {
            PodcastListFeature.State.Fetched(
                fetchedPodcasts = msg.podcasts
            ) to emptySet()
        }
        is PodcastListFeature.Msg.Ui.InitScreenHeader ->
            PodcastListFeature.State.ScreenHeader(
                categoryName = msg.categoryName, categoryImg = msg.categoryImg
            ) to setOf(PodcastListFeature.Cmd.OnInitScreenHeader(msg.fragment))

        is PodcastListFeature.Msg.Internal.RefreshingResult -> {
            PodcastListFeature.State.Fetched(
                fetchedPodcasts = msg.refreshed
            ) to emptySet()
        }

        is PodcastListFeature.Msg.Internal.ScreenHeaderResult -> {
            PodcastListFeature.State.ScreenHeader(
                categoryName = msg.categoryName,
                categoryImg = msg.categoryImg
            ) to emptySet()
        }

        is PodcastListFeature.Msg.Internal.ShowError -> {
            PodcastListFeature.State.Error(message = msg.message) to emptySet()
        }

    }
}