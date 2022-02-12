package ru.maksonic.rdpodcast.screen.podcast_list

import android.util.Log
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.base.presentation.architecture.CommandHandler
import ru.maksonic.rdpodcast.domain.podcast.PodcastInteractor
import ru.maksonic.rdpodcast.feature.podcast.PodcastAdapter
import ru.maksonic.rdpodcast.navigation.api.navigationData
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi
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
            is PodcastListFeature.Cmd.OnFetchCloudPodcast -> fetchPodcastList(
                consumer,
                cmd.category
            )
            is PodcastListFeature.Cmd.OnInitScreenHeader -> initScreenHeader(
                consumer,
                cmd.fragment)
        }
    }

    private suspend fun fetchPodcastList(
        consumer: (PodcastListFeature.Msg) -> Unit,
        category: String?
    ) {
        consumer(
            when (val result = interactor.fetchPodcastList(category)) {
                is Result.Success -> {
                    val podcasts = mapper.fromList(result.data)

                    PodcastListFeature.Msg.Internal.FetchingResult(podcasts)
                }
                is Result.Error -> {
                    PodcastListFeature.Msg.Internal.ShowError(result.exception)
                }
            }
        )
    }

    private fun initScreenHeader(
        consumer: (PodcastListFeature.Msg) -> Unit,
        fragment: Fragment,
    ) {
        val data = fragment.navigationData as? CategoryUi
        consumer(
            PodcastListFeature.Msg.Internal.ScreenHeaderResult(
                categoryName = data?.name,
                categoryImg = data?.image,
                fragment = fragment
            )
        )
    }
}
