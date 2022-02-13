package ru.maksonic.rdpodcast.data.categories

import ru.maksonic.rdpodcast.core.data.BaseRepository
import ru.maksonic.rdpodcast.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdpodcast.data.categories.cache.CategoryCache
import ru.maksonic.rdpodcast.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdpodcast.domain.categories.CategoryDomain
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoriesRepository @Inject constructor(
    private val cloudDataSource: CategoriesCloudDataSource,
    private val cacheDataSource: CategoriesCacheDataSource,
    mapper: CategoryDataToDomainMapper
) : BaseRepository<CategoryCache, CategoryData, CategoryDomain>(cacheDataSource, mapper) {
    override suspend fun cloudDataList(vararg data: Any?) = cloudDataSource.fetchCloudData()
    override suspend fun cacheDataList(vararg data: Any?) = cacheDataSource.fetchCacheData()
}