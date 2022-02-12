package ru.maksonic.rdpodcast.domain.podcast


/**
 * @Author: maksonic on 10.02.2022
 */
data class PodcastDomain(
    val id: Long,
    val categoryId: String? = null,
    val name: String? = null,
    val image: String? = null,
    val soundFile: String? = null,
)