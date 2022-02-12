package ru.maksonic.rdpodcast.core.logging

/**
 * @Author: maksonic on 12.02.2022
 */
fun Any.classTag(): String {
    val fullName = this::class.qualifiedName.toString()
    return if (fullName.contains("ru.maksonic.rdpodcast.")) {
        fullName.replace("ru.maksonic.rdpodcast.", "")
    } else {
        fullName
    }
}