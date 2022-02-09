package ru.maksonic.rdpodcast.data.categories.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maksonic.rdpodcast.data.DbCollection.CATEGORIES

/**
 * @Author: maksonic on 07.02.2022
 */
@Entity(tableName = CATEGORIES)
data class CategoryCache(
    @PrimaryKey
    val id: Long,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
)