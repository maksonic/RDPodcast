package ru.maksonic.rdpodcast.data.podcast

import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.base.Abstract
import ru.maksonic.rdpodcast.core.data.BaseRepository
import ru.maksonic.rdpodcast.domain.podcast.PodcastDomain

/**
 * @Author: maksonic on 10.02.2022
 */

class PodcastRepository(
    private val cloudDataSource: PodcastCloudDataSource,
    mapper: PodcastDataToDomainMapper
) : BaseRepository<Abstract.EmptyObject, PodcastData, PodcastDomain>(
    baseCacheDataSource = null, mapper) {

    override suspend fun cloudDataList(vararg data: Any?): Result<List<PodcastData>> =
        cloudDataSource.fetchCloudData(data[0].toString())

    override suspend fun cacheDataList(vararg data: Any?): Result<List<PodcastData>> {
        return Result.Success(emptyList())
    }
}
