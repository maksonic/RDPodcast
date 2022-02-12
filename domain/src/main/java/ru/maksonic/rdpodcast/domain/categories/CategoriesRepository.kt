package ru.maksonic.rdpodcast.domain.categories

import ru.maksonic.rdpodcast.core.Result

/**
 * @Author: maksonic on 07.02.2022
 */
typealias Categories = Result<List<CategoryDomain>>

interface CategoriesRepository {
    val tag: String
    suspend fun fetchCacheOrCloudCategories(): Categories
    suspend fun fetchCloudCategories(): Categories
}