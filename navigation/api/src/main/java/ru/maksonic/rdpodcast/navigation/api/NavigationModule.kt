package ru.maksonic.rdpodcast.navigation.api

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: maksonic on 06.02.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideNavigation(fragment: Fragment): Router = Router.Base(fragment)
}