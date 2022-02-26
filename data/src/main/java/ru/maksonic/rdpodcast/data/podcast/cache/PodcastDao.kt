package ru.maksonic.rdpodcast.data.podcast.cache

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdpodcast.core.data.BaseDao

/**
 * @Author: maksonic on 23.02.2022
 */
@Dao
abstract class PodcastDao : BaseDao<PodcastCache> {
    @Query("SELECT * FROM podcast_current_cache")
    abstract override suspend fun fetchCacheList(): List<PodcastCache>

    @Query("SELECT * FROM podcast_current_cache WHERE id = :item ORDER BY id")
    abstract override fun fetchCacheItem(item: Long): Flow<PodcastCache>

    @Query("DELETE FROM podcast_current_cache WHERE id = :id")
    abstract override suspend fun deleteCachedItemById(id: Long)
}
