package ru.maksonic.rdpodcast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.maksonic.rdpodcast.data.categories.cache.CategoryCache
import ru.maksonic.rdpodcast.data.categories.cache.CategoryDao

/**
 * @Author: maksonic on 07.02.2022
 */
@Database(entities = [CategoryCache::class], version = 1, exportSchema = false)
abstract class RdDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "rd-db"
    }
    abstract fun categoryDao(): CategoryDao
}