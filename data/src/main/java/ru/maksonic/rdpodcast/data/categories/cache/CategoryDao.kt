package ru.maksonic.rdpodcast.data.categories.cache

import androidx.room.Dao
import androidx.room.Query
import ru.maksonic.rdpodcast.core.data.BaseDao

/**
 * @Author: maksonic on 20.12.2021
 */
@Dao
abstract class CategoryDao : BaseDao<CategoryCache> {
    @Query("SELECT * FROM categories_cache")
    abstract override suspend fun fetchCacheList(): List<CategoryCache>
}
