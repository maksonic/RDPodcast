package ru.maksonic.rdpodcast.data.categories.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maksonic.rdpodcast.core.base.Abstract

/**
 * @Author: maksonic on 07.02.2022
 */
    @Entity(tableName = "categories_cache")
    data class CategoryCache(
        @PrimaryKey
        val id: Long,
        val categoryId: String = "",
        val name: String = "",
        val description: String = "",
        val image: String = "",
    ) : Abstract.CacheObject {

    }