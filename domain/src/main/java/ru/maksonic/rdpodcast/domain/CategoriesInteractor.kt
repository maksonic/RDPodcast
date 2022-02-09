package ru.maksonic.rdpodcast.domain

import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
interface CategoriesInteractor {
    suspend fun fetchCategories(): Categories
    suspend fun refreshCategories(): Categories

    class Base @Inject constructor(
        private val categoriesRepository: CategoriesRepository,
    ) : CategoriesInteractor {
        override suspend fun fetchCategories() = categoriesRepository.fetchCacheOrCloudCategories()
        override suspend fun refreshCategories() = categoriesRepository.fetchCloudCategories()
    }
}