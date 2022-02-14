package ru.maksonic.rdpodcast.core.ui

/**
 * @Author: maksonic on 08.02.2022
 */
interface Player {
    fun setPodcastInfo(categoryName: String, podcastName: String?, podcastImg: String?)
    fun playClicked()
}