package ru.maksonic.rdpodcast.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.core.data.Repository
import ru.maksonic.rdpodcast.domain.categories.CategoriesInteractor
import ru.maksonic.rdpodcast.domain.categories.CategoryDomain
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
    fun providePodcastInteractor(repository: PodcastRepository<PodcastDomain>): PodcastInteractor =
        PodcastInteractor.Base(repository)
}