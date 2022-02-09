package ru.maksonic.rdpodcast.domain

/**
 * @Author: maksonic on 07.02.2022
 */
data class CategoryDomain(
    val id: Long,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
)