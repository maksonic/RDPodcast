package ru.maksonic.rdpodcast.screen.categories

import ru.maksonic.rdpodcast.core.Mapper
import ru.maksonic.rdpodcast.domain.CategoryDomain
import ru.maksonic.rdpodcast.shared.ui_model.CategoryUi
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoryDomainToUiMapper @Inject constructor() : Mapper<CategoryDomain, CategoryUi> {
    override fun from(i: CategoryDomain?): CategoryUi =
        CategoryUi(id = i!!.id, name = i.name, description = i.description, image = i.image)

    override fun to(o: CategoryUi?) =
        CategoryDomain(id = o!!.id, name = o.name, description = o.description, image = o.image)
}