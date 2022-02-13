package ru.maksonic.rdpodcast.domain.podcast

import ru.maksonic.rdpodcast.core.base.Abstract


/**
 * @Author: maksonic on 10.02.2022
 */
data class PodcastDomain(
    val id: Long,
    val categoryId: String = "",
    val name: String = "",
    val image: String = "",
    val soundFile: String = "",
) : Abstract.DomainObject