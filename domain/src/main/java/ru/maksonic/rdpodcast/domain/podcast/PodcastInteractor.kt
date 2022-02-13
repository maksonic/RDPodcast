package ru.maksonic.rdpodcast.domain.podcast

import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.data.Repository
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
typealias Podcasts = Result<List<PodcastDomain>>

interface PodcastInteractor {
    suspend fun fetchPodcastList(category: String?): Podcasts

    class Base @Inject constructor(
        private val repository: Repository<PodcastDomain>) : PodcastInteractor {
        override suspend fun fetchPodcastList(category: String?): Podcasts =
            repository.fetchCloudData(category)
    }
}