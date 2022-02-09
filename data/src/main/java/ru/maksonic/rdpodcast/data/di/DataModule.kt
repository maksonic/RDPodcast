package ru.maksonic.rdpodcast.data.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.data.categories.CategoriesRepositoryImpl
import ru.maksonic.rdpodcast.data.categories.CategoryDataToDomainMapper
import ru.maksonic.rdpodcast.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdpodcast.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdpodcast.domain.CategoriesRepository

/**
 * @Author: maksonic on 24.11.2021
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideCategoriesRepository(
        cloud: CategoriesCloudDataSource,
        cache: CategoriesCacheDataSource,
        mapper: CategoryDataToDomainMapper,
    ): CategoriesRepository = CategoriesRepositoryImpl(cloud, cache, mapper)
}