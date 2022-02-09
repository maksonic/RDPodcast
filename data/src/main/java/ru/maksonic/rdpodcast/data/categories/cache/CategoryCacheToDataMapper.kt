package ru.maksonic.rdpodcast.data.categories.cache

import ru.maksonic.rdpodcast.core.Mapper
import ru.maksonic.rdpodcast.data.categories.CategoryData
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoryCacheToDataMapper @Inject constructor() : Mapper<CategoryCache, CategoryData> {

    override fun from(i: CategoryCache?) = CategoryData(
        id = i!!.id,
        name = i.name,
        description = i.description,
        image = i.image
    )

    override fun to(o: CategoryData?) = CategoryCache(
        id = o!!.id!!,
        name = o.name,
        description = o.description,
        image = o.image
    )
}