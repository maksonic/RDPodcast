package ru.maksonic.rdpodcast.screen.podcast_list

import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.base.presentation.architecture.CommandHandler
import ru.maksonic.rdpodcast.domain.podcast.PodcastInteractor
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
class PodcastsCmdHandler @Inject constructor(
    private val interactor: PodcastInteractor,
    private val mapper: PodcastDomainToUiMapper,
) : CommandHandler<PodcastListFeature.Cmd, PodcastListFeature.Msg> {

    override suspend fun execute(
        cmd: PodcastListFeature.Cmd,
        consumer: (PodcastListFeature.Msg) -> Unit,
    ) {
        when (cmd) {
            is PodcastListFeature.Cmd.OnFetchCloudPodcast ->
                fetchPodcastList(consumer, cmd.category)
        }
    }

    private suspend fun fetchPodcastList(
        consumer: (PodcastListFeature.Msg) -> Unit, category: String?
    ) {
        consumer(
            when (val result = interactor.fetchPodcastList(category)) {
                is Result.Success -> {
                    val podcasts = mapper.fromList(result.data)
                    PodcastListFeature.Msg.Internal.FetchingResult(podcasts, podcasts.size)
                }
                is Result.Error -> {
                    PodcastListFeature.Msg.Internal.ShowError(result.exception)
                }
            }
        )
    }
}
