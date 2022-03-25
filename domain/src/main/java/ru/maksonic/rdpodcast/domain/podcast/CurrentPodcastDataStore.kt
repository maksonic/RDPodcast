package ru.maksonic.rdpodcast.domain.podcast

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.Result
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @Author: maksonic on 24.03.2022
 */
interface CurrentPodcastDataStore {
    suspend fun setCurrentPodcastUi(category: String, podcast: PodcastDomain)
    suspend fun getCurrentPodcastUi(): PodcastDomain?
    var podcast: PodcastDomain?
    class Base @Inject constructor(
        val context: Context,
        private val rp: ResourceProvider
    ) : CurrentPodcastDataStore {
        private companion object {
            private val CATEGORY = stringPreferencesKey("datastore_podcast_category")
            private val NAME = stringPreferencesKey("datastore_podcast_name")
            private val IMAGE = stringPreferencesKey("datastore_podcast_image")
        }

        override var podcast: PodcastDomain? = PodcastDomain()

        override suspend fun setCurrentPodcastUi(
            category: String,
            podcast: PodcastDomain
        ) {
            rp.apply {
                context.datastore.edit { preferences ->
                    preferences[CATEGORY] = category
                    preferences[NAME] = podcast.name
                    preferences[IMAGE] = podcast.image
                }
            }
        }

        override suspend fun getCurrentPodcastUi(): PodcastDomain? {
            rp.apply {
                context.datastore.data.map { preferences ->
                    podcast = PodcastDomain(name = "preferences[NAME]" ?: "", image = "preferences[IMAGE]" ?: "")
                }
            }
            return podcast
        }
    }
}