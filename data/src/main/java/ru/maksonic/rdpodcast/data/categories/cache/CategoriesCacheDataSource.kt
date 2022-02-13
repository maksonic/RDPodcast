package ru.maksonic.rdpodcast.data.categories.cache

import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.data.BaseCacheDataSource
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.data.categories.CategoryData
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoriesCacheDataSource @Inject constructor(
    dao: CategoryDao,
    mapper: CategoryCacheToDataMapper,
    res: ResourceProvider,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCacheDataSource<CategoryCache, CategoryData>(dao, mapper, res, dispatcher)
