package ru.maksonic.rdpodcast.core.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdpodcast.core.Abstract

/**
 * @Author: maksonic on 13.02.2022
 */
@Dao
interface BaseDao<C : Abstract.CacheObject> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<C>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(data: C): Long

    suspend fun fetchCacheList(): List<@JvmSuppressWildcards C>

    fun fetchCacheItem(item: Long): Flow<@JvmSuppressWildcards C>

    @Delete
    suspend fun deleteAllCachedList(data: List<C>)

    @Delete
    suspend fun deleteCachedItemById(id: Long)
}