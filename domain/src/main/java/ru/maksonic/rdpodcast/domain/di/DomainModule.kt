package ru.maksonic.rdpodcast.domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.data.Repository
import ru.maksonic.rdpodcast.domain.categories.CategoriesInteractor
import ru.maksonic.rdpodcast.domain.categories.CategoryDomain
import ru.maksonic.rdpodcast.domain.podcast.CurrentPodcastDataStore
import ru.maksonic.rdpodcast.domain.podcast.PodcastDomain
import ru.maksonic.rdpodcast.domain.podcast.PodcastInteractor
import ru.maksonic.rdpodcast.domain.podcast.PodcastRepository
import javax.inject.Singleton

/**
 * @Author: maksonic on 07.02.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideCategoriesInteractor(repository: Repository<CategoryDomain>): CategoriesInteractor =
        CategoriesInteractor.Base(repository)

    @Singleton
    @Provides
    fun providePodcastInteractor(
        repository: PodcastRepository<PodcastDomain>,
        dataStore: CurrentPodcastDataStore.Base): PodcastInteractor =
        PodcastInteractor.Base(repository, dataStore = dataStore)

    @Singleton
    @Provides
    fun provideCurrentPodcastDS(@ApplicationContext context: Context, rp: ResourceProvider) =
        CurrentPodcastDataStore.Base(context, rp)
}