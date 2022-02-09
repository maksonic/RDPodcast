package ru.maksonic.rdpodcast.data.categories.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @Author: maksonic on 20.12.2021
 */
@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<CategoryCache>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategory(id: String): CategoryCache

    @Query("DELETE FROM categories")
    suspend fun deleteAllCachedCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<CategoryCache>) : List<Long>
}