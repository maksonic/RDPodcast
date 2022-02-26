package ru.maksonic.rdpodcast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.maksonic.rdpodcast.data.categories.cache.CategoryCache
import ru.maksonic.rdpodcast.data.categories.cache.CategoryDao
import ru.maksonic.rdpodcast.data.podcast.cache.PodcastCache
import ru.maksonic.rdpodcast.data.podcast.cache.PodcastDao

/**
 * @Author: maksonic on 07.02.2022
 */
@Database(entities = [CategoryCache::class, PodcastCache::class], version = 1, exportSchema = false)
abstract class RdDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "rd-db"
    }
    abstract fun categoryDao(): CategoryDao

    abstract fun podcastDao(): PodcastDao
}