package ru.maksonic.rdpodcast.data.podcast

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.Abstract
import ru.maksonic.rdpodcast.core.data.BaseRepository
import ru.maksonic.rdpodcast.core.data.Repository
import ru.maksonic.rdpodcast.data.podcast.cache.PodcastCacheDataSource
import ru.maksonic.rdpodcast.data.podcast.cloud.PodcastCloudDataSource
import ru.maksonic.rdpodcast.domain.podcast.PodcastDomain
import ru.maksonic.rdpodcast.domain.podcast.PodcastRepository
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
class PodcastRepositoryImpl @Inject constructor(
    private val cloud: PodcastCloudDataSource,
    private val cache: PodcastCacheDataSource,
    private val mapper: PodcastDataToDomainMapper,
) : BaseRepository<Abstract.EmptyObject, PodcastData, PodcastDomain>(
    baseCacheDataSource = null, mapper
), PodcastRepository<PodcastDomain> {

    override suspend fun cloudDataList(vararg data: Any?): Result<List<PodcastData>> {
        val selectedCategory = data[0].toString()
        return cloud.fetchCloudData(selectedCategory)
    }

    override suspend fun cacheDataList(vararg data: Any?): Result<List<PodcastData>> =
        Result.Success(emptyList())


    override suspend fun currentPodcast(podcast: PodcastDomain): Flow<Result<PodcastDomain>> =
        flow {
            val podcastData = mapper.to(podcast)
            cache.fetchCurrentPlayingPodcast(podcastData).collect { podcast ->
                when (podcast) {
                    is Result.Success -> {
                        val podcastDomain = mapper.from(podcast.data)
                        emit(Result.Success(podcastDomain))
                    }
                    is Result.Error -> emit(Result.Error(podcast.exception))
                }
            }
        }

}
