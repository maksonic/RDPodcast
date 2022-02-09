package ru.maksonic.rdpodcast.data.categories.cache


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.data.R
import ru.maksonic.rdpodcast.data.categories.CategoriesData
import ru.maksonic.rdpodcast.data.categories.CategoryData
import timber.log.Timber
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
interface CategoriesCacheDataSource {
    suspend fun getCacheCategories(): CategoriesData
    suspend fun saveCategoriesToCache(categories: List<CategoryData>)

    class Base @Inject constructor(
        private val categoryDao: CategoryDao,
        private val mapper: CategoryCacheToDataMapper,
        private val resourceProvider: ResourceProvider,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : CategoriesCacheDataSource {
        companion object {
            const val TAG = "CategoriesCacheDataSource"
        }

        override suspend fun getCacheCategories(): CategoriesData = withContext(dispatcher) {
            Timber.tag(TAG).i("FETCH FROM CACHE")
            try {
                val cacheList = categoryDao.getCategories()
                if (cacheList.isNullOrEmpty()) {
                    Timber.tag(TAG)
                        .e("|-| Empty categories CACHE list: ${cacheList.count()}")
                    Result.Error("Пустой лист!")
                } else {
                    val categories = mapper.fromList(cacheList)
                    Timber.tag(TAG)
                        .d("|+| Fetched from CACHE ${categories.count()} categories")
                    Result.Success(categories)
                }
            } catch (e: Exception) {
                Timber.tag(TAG).d("|-| ${e.localizedMessage}")
                Result.Error(e.localizedMessage)
            }
        }

        override suspend fun saveCategoriesToCache(categories: List<CategoryData>): Unit =
            withContext(dispatcher) {
                try {
                    if (categories.isNullOrEmpty()) {
                        Timber.tag(TAG).e("|-| Error saving ${categories.count()} categories")
                    } else {
                        val cachedList = mapper.toList(categories)
                        categoryDao.deleteAllCachedCategories()
                        categoryDao.insertAll(cachedList)
                        Timber.tag(TAG).d("|+| Saved ${cachedList.count()} categories")
                    }
                } catch (e: Exception) {
                    Timber.tag(TAG).d("|-| ${e.localizedMessage}")
                }
            }
    }
}
