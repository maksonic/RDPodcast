package ru.maksonic.rdpodcast.data.podcast.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maksonic.rdpodcast.core.Abstract

/**
 * @Author: maksonic on 20.02.2022
 */
@Entity(tableName = "podcast_current_cache")
data class PodcastCache(
    @PrimaryKey
    val id: Long,
    val categoryId: String = "",
    val name: String = "",
    val image: String = "",
    val soundFile: String = "",
) : Abstract.CacheObject