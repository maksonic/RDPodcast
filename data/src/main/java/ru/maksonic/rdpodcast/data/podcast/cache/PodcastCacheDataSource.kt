package ru.maksonic.rdpodcast.data.podcast.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.data.podcast.PodcastData
import javax.inject.Inject

/**
 * @Author: maksonic on 23.02.2022
 */
typealias CurrentPodcast = Flow<Result<PodcastData>>

interface PodcastCacheDataSource {
    suspend fun saveCurrentPlayingPodcast(podcast: PodcastData)
    suspend fun fetchCurrentPlayingPodcast(podcast: PodcastData): CurrentPodcast

    class Base @Inject constructor(
        private val dao: PodcastDao,
        private val mapper: PodcastCacheToDataMapper,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : PodcastCacheDataSource {
        override suspend fun fetchCurrentPlayingPodcast(podcast: PodcastData): CurrentPodcast =
            flow {
                try {
                    dao.fetchCacheItem(podcast.id as Long).collect { cached ->
                        val podcastData = mapper.from(cached)
                        emit(Result.Success(podcastData))
                    }
                } catch (e: Exception) {
                    emit(Result.Error(e.localizedMessage.toString()))
                }
            }.flowOn(dispatcher)

        override suspend fun saveCurrentPlayingPodcast(podcast: PodcastData) {
            val cachedPodcast = mapper.to(podcast)
            dao.insertItem(cachedPodcast)
        }
    }
}