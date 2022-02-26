package ru.maksonic.rdpodcast.domain.podcast

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdpodcast.core.Result
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
typealias PodcastList = Result<List<PodcastDomain>>
typealias CurrentPodcast = Flow<Result<PodcastDomain>>

interface PodcastInteractor {

    suspend fun fetchPodcastList(category: String?): PodcastList
    suspend fun currentPodcast(podcast: PodcastDomain): CurrentPodcast

    class Base @Inject constructor(
        private val repository: PodcastRepository<PodcastDomain>
    ) : PodcastInteractor {
        override suspend fun fetchPodcastList(category: String?): PodcastList =
            repository.fetchCloudData(category)

        override suspend fun currentPodcast(podcast: PodcastDomain): CurrentPodcast =
            repository.currentPodcast(podcast)
    }
}