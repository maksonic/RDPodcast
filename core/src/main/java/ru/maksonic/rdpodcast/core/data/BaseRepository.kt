package ru.maksonic.rdpodcast.core.data

import ru.maksonic.rdpodcast.core.Mapper
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.base.Abstract
import java.lang.Exception

/**
 * @Author: maksonic on 12.02.2022
 */

abstract class BaseRepository<C: Abstract.CacheObject, D : Abstract.DataObject, E : Abstract.DomainObject>(
    private val baseCacheDataSource: BaseCacheDataSource<C, D>?,
    private val dataToDomainMapper: Mapper<D, E>
) : Repository<E> {

    override suspend fun fetchCloudData(vararg data: Any?): Result<List<E>> = try {
        when (val cloud = cloudDataList(*data)) {
            is Result.Success -> {
                val domainList = dataToDomainMapper.fromList(cloud.data)
                Result.Success(domainList)
            }
            is Result.Error -> Result.Error(cloud.exception)
        }
    } catch (e: Exception) {
        Result.Error(e.localizedMessage.toString())
    }

    override suspend fun fetchCacheOrCloudData(vararg data: Any?): Result<List<E>> = try {
        when (val cacheResult = cacheDataList()) {
            is Result.Success -> {
                val cachedList = dataToDomainMapper.fromList(cacheResult.data)
                Result.Success(cachedList)
            }
            is Result.Error -> {
                when (val cloudResult = cloudDataList(*data)) {
                    is Result.Success -> {
                        val cloudList = dataToDomainMapper.fromList(cloudResult.data)
                        baseCacheDataSource!!.saveDataToCache(dataToDomainMapper.toList(cloudList))
                        Result.Success(cloudList)
                    }
                    is Result.Error -> Result.Error(cloudResult.exception)
                }
            }
        }
    } catch (e: Exception) {
        Result.Error(e.localizedMessage.toString())
    }
    protected abstract suspend fun cloudDataList(vararg data: Any?): Result<List<D>>
    protected abstract suspend fun cacheDataList(vararg data: Any?): Result<List<D>>
}