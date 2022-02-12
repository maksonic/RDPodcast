package ru.maksonic.rdpodcast.data.podcast

import kotlinx.coroutines.delay
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.data.Delay
import ru.maksonic.rdpodcast.domain.podcast.Categories
import ru.maksonic.rdpodcast.domain.podcast.PodcastRepository
import java.lang.Exception
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
class PodcastRepositoryImpl @Inject constructor(
    private val podcastCloudDataSource: PodcastCloudDataSource,
    val mapper: PodcastDataToDomainMapper
) : PodcastRepository {

    override suspend fun fetchCloudPodcast(categoryDomain: String?): Categories = try {
        when (val result = podcastCloudDataSource.getCloudPodcast(categoryDomain)) {
            is Result.Success -> {
                val podcastList = mapper.fromList(result.data)
                Result.Success(podcastList)
            }
            is Result.Error -> Result.Error(result.exception)
        }
    } catch (e: Exception) {
        Result.Error(e.localizedMessage.toString())
    }
}