package ru.maksonic.rdpodcast.domain.podcast

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdpodcast.core.Abstract
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.data.Repository

/**
 * @Author: maksonic on 25.02.2022
 */
interface PodcastRepository<E: Abstract.DomainObject> : Repository<E> {
    suspend fun currentPodcast(podcast: PodcastDomain): Flow<Result<PodcastDomain>>
}
