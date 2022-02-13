package ru.maksonic.rdpodcast.core.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.maksonic.rdpodcast.core.Mapper
import ru.maksonic.rdpodcast.core.R
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.base.Abstract
import ru.maksonic.rdpodcast.core.logging.classTag
import timber.log.Timber
import java.lang.Exception

/**
 * @Author: maksonic on 13.02.2022
 */
typealias BaseCacheDataSource<C, D> = CacheDataSource.Base<C, D>

interface CacheDataSource<C, D> {
    suspend fun fetchCacheData(vararg data: Any?): Result<List<D>>
    suspend fun saveDataToCache(data: List<D>)

    abstract class Base<C : Abstract.CacheObject, D : Abstract.DataObject>(
        private val dao: BaseDao<C>?,
        private val mapper: Mapper<C, D>,
        private val res: ResourceProvider,
        private val dispatcher: CoroutineDispatcher
    ) : CacheDataSource<C, D> {
        private val tag = classTag()

        override suspend fun fetchCacheData(vararg data: Any?): Result<List<D>> =
            withContext(dispatcher) {
                Timber.tag(tag).i(res.getString(R.string.log_start_fetching_from_cache))
                try {
                    val cacheRawList = dao?.fetchCacheList()
                    if (cacheRawList.isNullOrEmpty()) {
                        Timber.tag(tag).e(res
                            .getString(R.string.log_error_empty_cache_list))
                        Result.Error(res.getString(R.string.error_empty_data_cache_list))
                    } else {
                        val cachedList = mapper.fromList(cacheRawList)
                        Timber.tag(tag).d(res
                            .getString(R.string.log_success_fetching_from_cache, cachedList.size))
                        Result.Success(cachedList)
                    }
                } catch (e: Exception) {
                    Timber.tag(tag).e(res.getString(R.string.log_error_fetching_from_cache,
                        e.localizedMessage))
                    Result.Error(e.localizedMessage.toString())
                }
            }

        override suspend fun saveDataToCache(data: List<D>): Unit = withContext(dispatcher) {
            try {
                if (data.isNullOrEmpty()) {
                    Timber.tag(tag)
                        .e(res.getString(R.string.log_error_empty_saved_cache_list))
                } else {
                    val cachedList = mapper.toList(data)
                    dao?.deleteAllCachedCategories(cachedList)
                    dao?.insertAll(cachedList)
                    Timber.tag(tag).d(res.getString(R.string.log_success_saving_to_cache,
                        cachedList.size))
                }
            } catch (e: Exception) {
                Timber.tag(tag).e(res.getString(R.string.error_empty_data_cache_list))
            }
        }
    }
}