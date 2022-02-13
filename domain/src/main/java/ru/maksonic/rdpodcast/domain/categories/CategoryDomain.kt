package ru.maksonic.rdpodcast.domain.categories

import ru.maksonic.rdpodcast.core.base.Abstract

/**
 * @Author: maksonic on 07.02.2022
 */
data class CategoryDomain(
    val id: Long,
    val categoryId: String = "",
    val name: String = "",
    val description: String = "",
    val image: String = "",
) : Abstract.DomainObject