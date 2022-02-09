package ru.maksonic.rdpodcast.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.data.FirebaseApi
import ru.maksonic.rdpodcast.data.categories.cloud.CategoriesCloudDataSource
import javax.inject.Singleton

/**
 * @Author: maksonic on 20.12.2021
 */
@Module
@InstallIn(SingletonComponent::class)
object CloudModule {

    @Singleton
    @Provides
    fun provideFirebaseApi(): FirebaseApi = FirebaseApi.Base()

    @ExperimentalCoroutinesApi
    @Provides
    fun provideCategoriesCloudDataSource(
        api: FirebaseApi,
        provider: ResourceProvider,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): CategoriesCloudDataSource =
        CategoriesCloudDataSource.Base(api, provider, dispatcher)

}