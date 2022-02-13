package ru.maksonic.rdpodcast.domain.categories

import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.data.Repository
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
typealias Categories = Result<List<CategoryDomain>>

interface CategoriesInteractor {
    suspend fun fetchCategories(): Categories
    suspend fun refreshCategories(): Categories

    class Base @Inject constructor(
        private val repository: Repository<CategoryDomain>,
    ) : CategoriesInteractor {
        override suspend fun fetchCategories() = repository.fetchCacheOrCloudData()
        override suspend fun refreshCategories() = repository.fetchCloudData()
    }
}