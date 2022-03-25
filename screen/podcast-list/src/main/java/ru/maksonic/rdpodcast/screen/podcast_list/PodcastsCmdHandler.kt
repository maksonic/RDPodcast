package ru.maksonic.rdpodcast.screen.podcast_list

import android.util.Log
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.base.presentation.architecture.CommandHandler
import ru.maksonic.rdpodcast.domain.podcast.PodcastInteractor
import ru.maksonic.rdpodcast.shared.ui_model.PodcastUi
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

            is PodcastListFeature.Cmd.SelectCurrentPodcast ->
                setCurrentPodcast(consumer, category = cmd.category, podcast = cmd.podcast)
        }
    }

    private suspend fun setCurrentPodcast(
        consumer: (PodcastListFeature.Msg) -> Unit,
        category: String, podcast: PodcastUi?
    ) {
        val podcastDomain = mapper.to(podcast!!)

        consumer(
            interactor.setCurrentPodcastUi(category, podcastDomain).let {
                PodcastListFeature.Msg.Ui.SelectPodcast(category, podcast)
            }

        )
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
