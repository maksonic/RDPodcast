package ru.maksonic.rdpodcast.domain

import ru.maksonic.rdpodcast.core.Result

/**
 * @Author: maksonic on 07.02.2022
 */
typealias Categories = Result<List<CategoryDomain>>

interface CategoriesRepository {
    suspend fun fetchCacheOrCloudCategories(): Categories
    suspend fun fetchCloudCategories(): Categories
}