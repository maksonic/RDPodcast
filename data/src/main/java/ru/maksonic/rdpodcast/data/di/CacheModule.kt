package ru.maksonic.rdpodcast.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.data.BaseCacheDataSource
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.data.RdDatabase
import ru.maksonic.rdpodcast.data.categories.CategoryData
import ru.maksonic.rdpodcast.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdpodcast.data.categories.cache.CategoryCache
import ru.maksonic.rdpodcast.data.categories.cache.CategoryCacheToDataMapper
import ru.maksonic.rdpodcast.data.categories.cache.CategoryDao
import ru.maksonic.rdpodcast.data.podcast.cache.PodcastCacheDataSource
import ru.maksonic.rdpodcast.data.podcast.cache.PodcastCacheToDataMapper
import ru.maksonic.rdpodcast.data.podcast.cache.PodcastDao
import javax.inject.Singleton

/**
 * @Author: maksonic on 20.12.2021
 */
@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, RdDatabase::class.java, RdDatabase.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun provideCategoryDao(db: RdDatabase): CategoryDao = db.categoryDao()

    @Singleton
    @Provides
    fun providePodcastDao(db: RdDatabase): PodcastDao = db.podcastDao()

    @Singleton
    @Provides
    fun provideCategoriesCacheDataSource(
        dao: CategoryDao,
        mapper: CategoryCacheToDataMapper,
        provider: ResourceProvider,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): BaseCacheDataSource<CategoryCache, CategoryData> =
        CategoriesCacheDataSource(dao, mapper, provider, dispatcher)

    @Singleton
    @Provides
    fun providePodcastCacheDataSource(
        dao: PodcastDao,
        mapper: PodcastCacheToDataMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): PodcastCacheDataSource =
        PodcastCacheDataSource.Base(dao, mapper, dispatcher)
}