package ru.maksonic.rdpodcast.data.categories.cloud

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.data.BaseCloudDataSource
import ru.maksonic.rdpodcast.core.data.NetworkException
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.data.FirebaseApi
import ru.maksonic.rdpodcast.data.categories.CategoryData
import ru.maksonic.rdpodcast.data.categories.cache.CategoryCache
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
class CategoriesCloudDataSource @Inject constructor(
    private val api: FirebaseApi,
    res: ResourceProvider,
    networkException: NetworkException,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCloudDataSource<CategoryData>(api, res, networkException, dispatcher) {

    override suspend fun response(vararg data: Any?): QuerySnapshot =
        api.categoriesCollection.orderBy("id").get(Source.SERVER).await()

    override suspend fun dataResult(vararg data: Any?): List<CategoryData> =
        response().toObjects(CategoryData::class.java).toList()
}