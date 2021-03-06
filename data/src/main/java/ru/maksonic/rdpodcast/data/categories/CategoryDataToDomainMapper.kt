package ru.maksonic.rdpodcast.data.categories

import ru.maksonic.rdpodcast.core.Abstract
import ru.maksonic.rdpodcast.core.Mapper
import ru.maksonic.rdpodcast.domain.categories.CategoryDomain
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoryDataToDomainMapper @Inject constructor() : Mapper<CategoryData, CategoryDomain> {
    override fun from(i: CategoryData) = CategoryDomain(
        id = i.id!!,
        categoryId = i.categoryId,
        name = i.name,
        description = i.description,
        image = i.image
    )

    override fun to(o: CategoryDomain) = CategoryData(
        id = o.id,
        categoryId = o.categoryId,
        name = o.name,
        description = o.description,
        image = o.image
    )
}