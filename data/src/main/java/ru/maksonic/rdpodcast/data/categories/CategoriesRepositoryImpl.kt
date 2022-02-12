package ru.maksonic.rdpodcast.data.categories

import kotlinx.coroutines.delay
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.data.Delay
import ru.maksonic.rdpodcast.core.logging.classTag
import ru.maksonic.rdpodcast.data.R
import ru.maksonic.rdpodcast.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdpodcast.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdpodcast.domain.categories.Categories
import ru.maksonic.rdpodcast.domain.categories.CategoriesRepository
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
    override val tag = this.classTag()


    override suspend fun fetchCacheOrCloudCategories(): Categories = try {
        when (val cacheResult = cacheDataSource.getCacheCategories()) {
            is Result.Success -> {
                val categories = mapper.fromList(cacheResult.data)
                Result.Success(categories)
            }
            is Result.Error -> fetchCloudCategories()
        }
    } catch (e: Exception) {
        Result.Error(e.localizedMessage)
    }

    override suspend fun fetchCloudCategories(): Categories = try {
        delay(Delay.baseRequestDelay)
        when (val cloudResult = cloudDataSource.getCloudCategories()) {
            is Result.Success -> {
                val categories = mapper.fromList(cloudResult.data)
                cacheDataSource.saveCategoriesToCache(cloudResult.data)
                Result.Success(categories)
            }
            is Result.Error -> {
                Result.Error(cloudResult.exception)
            }
        }
    } catch (e: Exception) {
        Result.Error(e.localizedMessage)
    }
}