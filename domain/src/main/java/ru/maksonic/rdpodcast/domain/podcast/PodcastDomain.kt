package ru.maksonic.rdpodcast.domain.podcast

import ru.maksonic.rdpodcast.core.Abstract

/**
 * @Author: maksonic on 10.02.2022
 */
data class PodcastDomain(
    val id: Long? = null,
    val categoryId: String = "",
    val name: String = "",
    val image: String = "",
    val soundFile: String = "",
    val isCurrentPlaying: Boolean = false,
    val isFavorite: Boolean = false,
    val isDownloaded: Boolean = false
) : Abstract.DomainObject