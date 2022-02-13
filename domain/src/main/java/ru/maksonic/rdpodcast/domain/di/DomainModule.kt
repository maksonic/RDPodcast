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

/**
 * @Author: maksonic on 07.02.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideCategoriesInteractor(repository: Repository<CategoryDomain>): CategoriesInteractor =
        CategoriesInteractor.Base(repository)

    @Provides
    fun providePodcastInteractor(repository: Repository<PodcastDomain>): PodcastInteractor =
        PodcastInteractor.Base(repository)
}