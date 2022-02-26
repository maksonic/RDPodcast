package ru.maksonic.rdpodcast.data.podcast.cache

import ru.maksonic.rdpodcast.core.Mapper
import ru.maksonic.rdpodcast.data.podcast.PodcastData
import javax.inject.Inject

/**
 * @Author: maksonic on 20.02.2022
 */
class PodcastCacheToDataMapper @Inject constructor() : Mapper<PodcastCache, PodcastData> {
    override fun from(i: PodcastCache) = PodcastData(
        id = i.id,
        categoryId = i.categoryId,
        name = i.name,
        image = i.image,
        soundFile = i.soundFile
    )

    override fun to(o: PodcastData) = PodcastCache(
        id = o.id!!,
        categoryId = o.categoryId,
        name = o.name,
        image = o.image,
        soundFile = o.soundFile
    )
}