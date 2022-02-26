package ru.maksonic.rdpodcast.data.categories.cache

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdpodcast.core.data.BaseDao
import ru.maksonic.rdpodcast.data.podcast.cache.PodcastCache

/**
 * @Author: maksonic on 20.12.2021
 */
@Dao
abstract class CategoryDao : BaseDao<CategoryCache> {
    @Query("SELECT * FROM categories_cache")
    abstract override suspend fun fetchCacheList(): List<CategoryCache>

    @Query("SELECT * FROM categories_cache WHERE id = :item")
    abstract override fun fetchCacheItem(item: Long): Flow<CategoryCache>

    @Query("DELETE FROM categories_cache WHERE id = :id")
    abstract override suspend fun deleteCachedItemById(id: Long)
}
