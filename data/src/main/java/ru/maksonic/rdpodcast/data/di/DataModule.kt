package ru.maksonic.rdpodcast.data.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.core.data.Repository
import ru.maksonic.rdpodcast.data.categories.CategoriesRepository
import ru.maksonic.rdpodcast.data.categories.CategoryDataToDomainMapper
import ru.maksonic.rdpodcast.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdpodcast.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdpodcast.data.podcast.PodcastDataToDomainMapper
import ru.maksonic.rdpodcast.data.podcast.PodcastRepositoryImpl
import ru.maksonic.rdpodcast.data.podcast.cache.PodcastCacheDataSource
import ru.maksonic.rdpodcast.data.podcast.cloud.PodcastCloudDataSource
import ru.maksonic.rdpodcast.domain.categories.CategoryDomain
import ru.maksonic.rdpodcast.domain.podcast.PodcastDomain
import ru.maksonic.rdpodcast.domain.podcast.PodcastRepository
import javax.inject.Singleton

/**
 * @Author: maksonic on 24.11.2021
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideCategoriesRepository(
        cloud: CategoriesCloudDataSource,
        cache: CategoriesCacheDataSource,
        mapper: CategoryDataToDomainMapper,
    ): Repository<CategoryDomain> = CategoriesRepository(cloud, cache, mapper)

    @Singleton
    @Provides
    fun providePodcastBaseRepository(
        cloud: PodcastCloudDataSource,
        cache: PodcastCacheDataSource,
        mapper: PodcastDataToDomainMapper,
    ): Repository<PodcastDomain> = PodcastRepositoryImpl(cloud, cache, mapper)

    @Singleton
    @Provides
    fun providePodcastRepository(
        cloud: PodcastCloudDataSource,
        cache: PodcastCacheDataSource,
        mapper: PodcastDataToDomainMapper
    ): PodcastRepository<PodcastDomain> = PodcastRepositoryImpl(cloud, cache, mapper)
}