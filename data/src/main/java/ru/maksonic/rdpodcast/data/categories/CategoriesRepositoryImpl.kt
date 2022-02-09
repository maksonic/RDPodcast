package ru.maksonic.rdpodcast.data.categories

import kotlinx.coroutines.delay
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.data.Delay
import ru.maksonic.rdpodcast.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdpodcast.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdpodcast.domain.Categories
import ru.maksonic.rdpodcast.domain.CategoriesRepository
import timber.log.Timber
import java.lang.Exception

/**
 * @Author: maksonic on 07.02.2022
 */
typealias CategoriesData = Result<List<CategoryData>>

class CategoriesRepositoryImpl(
    private val cloudDataSource: CategoriesCloudDataSource,
    private val cacheDataSource: CategoriesCacheDataSource,
    private val mapper: CategoryDataToDomainMapper,
) : CategoriesRepository {
    companion object {
        const val TAG = "Categories Repository Impl"
    }

    override suspend fun fetchCacheOrCloudCategories(): Categories = try {
        Timber.tag(TAG).i("Try fetch CACHE categories")
        when (val cacheResult = cacheDataSource.getCacheCategories()) {
            is Result.Success -> {
                val categories = mapper.fromList(cacheResult.data)
                Result.Success(categories)
            }
            is Result.Error -> fetchCloudCategories()
        }
    } catch (e: Exception) {
        Timber.tag(TAG).e("|-| ${e.localizedMessage}")
        Result.Error(e.localizedMessage)
    }

    override suspend fun fetchCloudCategories(): Categories = try {
        Timber.tag(TAG).i("Try fetch CLOUD categories")
        delay(Delay.baseRequestDelay)
        when (val cloudResult = cloudDataSource.getCloudCategories()) {
            is Result.Success -> {
                val categories = mapper.fromList(cloudResult.data)
                cacheDataSource.saveCategoriesToCache(cloudResult.data)
                Result.Success(categories)
            }
            is Result.Error -> {
                Timber.tag(TAG).e("|-| ${cloudResult.exception}")
                Result.Error(cloudResult.exception)
            }
        }
    } catch (e: Exception) {
        Timber.tag(TAG).e("|-| ${e.localizedMessage}")
        Result.Error(e.localizedMessage)
    }
}