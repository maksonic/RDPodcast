package ru.maksonic.rdpodcast.data.podcast

import ru.maksonic.rdpodcast.core.Mapper
import ru.maksonic.rdpodcast.domain.podcast.PodcastDomain
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
class PodcastDataToDomainMapper @Inject constructor() : Mapper<PodcastData, PodcastDomain> {
    override fun from(i: PodcastData?) = PodcastDomain(
        id = i!!.id!!,
        categoryId = i.categoryId,
        name = i.name,
        image = i.image,
        soundFile = i.soundFile
    )

    override fun to(o: PodcastDomain?) = PodcastData(
        id = o!!.id,
        categoryId = o.categoryId,
        name = o.name,
        image = o.image,
        soundFile = o.soundFile
    )
}