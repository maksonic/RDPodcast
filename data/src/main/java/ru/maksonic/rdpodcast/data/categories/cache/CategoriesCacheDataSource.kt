package ru.maksonic.rdpodcast.data.categories.cache


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.core.logging.classTag
import ru.maksonic.rdpodcast.data.R
import ru.maksonic.rdpodcast.data.categories.CategoriesData
import ru.maksonic.rdpodcast.data.categories.CategoryData
import timber.log.Timber
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
interface CategoriesCacheDataSource {
    val tag: String
    suspend fun getCacheCategories(): CategoriesData
    suspend fun saveCategoriesToCache(categories: List<CategoryData>)

    class Base @Inject constructor(
        private val categoryDao: CategoryDao,
        private val mapper: CategoryCacheToDataMapper,
        private val resProvider: ResourceProvider,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : CategoriesCacheDataSource {
        override val tag = this.classTag()

        override suspend fun getCacheCategories(): CategoriesData = withContext(dispatcher) {
            Timber.tag(tag).i(resProvider.getString(R.string.log_start_fetching_from_cache))
            try {
                val cacheList = categoryDao.getCategories()
                if (cacheList.isNullOrEmpty()) {
                    Timber.tag(tag).i(resProvider.getString(R.string.log_error_empty_cache_list))
                    Result.Error(resProvider.getString(R.string.error_empty_data_cache_list))
                } else {
                    val categories = mapper.fromList(cacheList)
                    Timber.tag(tag).d(resProvider.getString(
                        R.string.log_success_fetching_from_cache, categories.size)
                    )
                    Result.Success(categories)
                }
            } catch (e: Exception) {
                Timber.tag(tag).e(resProvider.getString(R.string.log_error_fetching_from_cache,
                        e.localizedMessage)
                )
                Result.Error(e.localizedMessage)
            }
        }

        override suspend fun saveCategoriesToCache(categories: List<CategoryData>): Unit =
            withContext(dispatcher) {
                Timber.tag(tag).i(resProvider.getString(R.string.log_start_saving_to_cache))
                try {
                    if (categories.isNullOrEmpty()) {
                        Timber.tag(tag)
                            .i(resProvider.getString(R.string.log_error_empty_saved_cache_list))
                    } else {
                        val cachedList = mapper.toList(categories)
                        categoryDao.deleteAllCachedCategories()
                        categoryDao.insertAll(cachedList)
                        Timber.tag(tag).i(resProvider.getString(R.string.log_success_saving_to_cache,
                                cachedList.size))
                    }
                } catch (e: Exception) {
                    Timber.tag(tag).e(resProvider.getString(R.string.error_empty_data_cache_list))
                }
            }
    }
}
