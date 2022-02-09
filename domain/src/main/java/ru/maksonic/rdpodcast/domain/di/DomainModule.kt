package ru.maksonic.rdpodcast.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.domain.CategoriesInteractor
import ru.maksonic.rdpodcast.domain.CategoriesRepository

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
}