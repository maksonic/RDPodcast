package ru.maksonic.rdpodcast.core.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.C.CONTENT_TYPE_MUSIC
import com.google.android.exoplayer2.C.USAGE_MEDIA
import com.google.android.exoplayer2.audio.AudioAttributes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdpodcast.core.PlayerBottomSheetStateListener
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.data.NetworkException
import ru.maksonic.rdpodcast.core.R
import javax.inject.Singleton

/**
 * @Author: maksonic on 07.02.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider =
        ResourceProvider.Base(context)

    @Singleton
    @Provides
    fun provideNetworkException(): NetworkException = NetworkException.Base()

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.podcast_image)
                .error(R.drawable.podcast_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )

    @Singleton
    @Provides
    fun providePlayerBottomSheetListener(fragment: Fragment): PlayerBottomSheetStateListener =
        PlayerBottomSheetStateListener.Base(fragment)
}