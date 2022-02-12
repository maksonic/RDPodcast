package ru.maksonic.rdpodcast.domain.podcast

import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.domain.categories.CategoryDomain

/**
 * @Author: maksonic on 10.02.2022
 */
typealias Categories = Result<List<PodcastDomain>>

interface PodcastRepository {
    suspend fun fetchCloudPodcast(categoryDomain: String?): Categories
}