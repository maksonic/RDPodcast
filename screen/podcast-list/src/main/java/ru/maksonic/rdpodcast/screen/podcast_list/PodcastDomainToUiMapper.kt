package ru.maksonic.rdpodcast.screen.podcast_list

import ru.maksonic.rdpodcast.core.Mapper
import ru.maksonic.rdpodcast.domain.podcast.PodcastDomain
import ru.maksonic.rdpodcast.shared.ui_model.PodcastUi
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
class PodcastDomainToUiMapper @Inject constructor() : Mapper<PodcastDomain, PodcastUi> {
    override fun from(i: PodcastDomain) = PodcastUi(
        id = i.id,
        categoryId = i.categoryId,
        name = i.name,
        image = i.image,
        soundFile = i.soundFile
    )

    override fun to(o: PodcastUi) = PodcastDomain(
        id = o.id,
        categoryId = o.categoryId,
        name = o.name,
        image = o.image,
        soundFile = o.soundFile
    )
}