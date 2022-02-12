package ru.maksonic.rdpodcast.domain.podcast

import ru.maksonic.rdpodcast.domain.categories.CategoryDomain
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
interface PodcastInteractor {
    suspend fun fetchPodcastList(category: String?): Categories

    class Base @Inject constructor(
        private val repository: PodcastRepository,
    ) : PodcastInteractor {
        override suspend fun fetchPodcastList(category: String?): Categories =
            repository.fetchCloudPodcast(category)
    }
}