package ru.maksonic.rdpodcast.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.domain.categories.CategoriesInteractor
import ru.maksonic.rdpodcast.domain.categories.CategoriesRepository
import ru.maksonic.rdpodcast.domain.podcast.PodcastInteractor
import ru.maksonic.rdpodcast.domain.podcast.PodcastRepository

/**
 * @Author: maksonic on 07.02.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideCategoriesInteractor(
        categoriesRepository: CategoriesRepository,
    ): CategoriesInteractor =
        CategoriesInteractor.Base(categoriesRepository)

    @Provides
    fun providePodcastInteractor(
        repository: PodcastRepository,
    ): PodcastInteractor =
        PodcastInteractor.Base(repository)
}