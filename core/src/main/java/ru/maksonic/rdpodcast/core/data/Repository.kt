package ru.maksonic.rdpodcast.core.data

import ru.maksonic.rdpodcast.core.Abstract
import ru.maksonic.rdpodcast.core.Result

/**
 * @Author: maksonic on 12.02.2022
 */
interface Repository<E : Abstract.DomainObject> {
    suspend fun fetchCloudData(vararg data: Any?): Result<List<E>>
    suspend fun fetchCacheOrCloudData(vararg data: Any?): Result<List<E>>
}