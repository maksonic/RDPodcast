package ru.maksonic.rdpodcast.data.podcast

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.data.BaseCloudDataSource
import ru.maksonic.rdpodcast.core.data.NetworkException
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.data.FirebaseApi
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
class PodcastCloudDataSource @Inject constructor(
    private val api: FirebaseApi,
    res: ResourceProvider,
    exceptionProvider: NetworkException,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCloudDataSource<PodcastData>(api, res, exceptionProvider, dispatcher) {

    override suspend fun response(vararg data: Any?): QuerySnapshot {
        val category = data[0].toString()
        return api.categoriesCollection.document(category)
            .collection(api.podcastCollectionName).get(Source.SERVER)
            .await()
    }

    override suspend fun dataResult(vararg data: Any?): List<PodcastData> {
        return response(data[0].toString()).toObjects(PodcastData::class.java).toList()
    }
}
