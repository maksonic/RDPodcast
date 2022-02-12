package ru.maksonic.rdpodcast.navigation.api

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.navigation.api.Router

/**
 * @Author: maksonic on 06.02.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    fun provideNavigation(fragment: Fragment): Router = Router.Base(fragment)
}