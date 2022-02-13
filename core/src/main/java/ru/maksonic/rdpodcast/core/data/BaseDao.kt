package ru.maksonic.rdpodcast.core.data

import androidx.room.*
import ru.maksonic.rdpodcast.core.base.Abstract

/**
 * @Author: maksonic on 13.02.2022
 */
@Dao
interface BaseDao<C : Abstract.CacheObject> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<C>): List<Long>

    @Delete
    suspend fun deleteAllCachedCategories(data: List<C>)

    suspend fun fetchCacheList(): List<@JvmSuppressWildcards C>
}
