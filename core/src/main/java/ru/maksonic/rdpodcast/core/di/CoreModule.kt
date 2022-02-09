package ru.maksonic.rdpodcast.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.core.ResourceProvider

/**
 * @Author: maksonic on 07.02.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider =
        ResourceProvider.Base(context)
}