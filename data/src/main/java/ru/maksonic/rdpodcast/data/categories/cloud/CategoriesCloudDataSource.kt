package ru.maksonic.rdpodcast.data.categories.cloud

import com.google.firebase.firestore.Source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.data.NetworkException.FAILED_TO_GET_DOC
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.data.FirebaseApi
import ru.maksonic.rdpodcast.data.R
import ru.maksonic.rdpodcast.data.categories.CategoriesData
import ru.maksonic.rdpodcast.data.categories.CategoryData
import timber.log.Timber
import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
interface CategoriesCloudDataSource {
    val tag: String?
    suspend fun getCloudCategories(): CategoriesData

    @ExperimentalCoroutinesApi
    class Base @Inject constructor(
        private val firebaseApi: FirebaseApi,
        private val resProvider: ResourceProvider,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : CategoriesCloudDataSource {
        override val tag = this::class.simpleName.toString()

        override suspend fun getCloudCategories(): CategoriesData = withContext(dispatcher) {
            Timber.tag(tag).i(resProvider.getString(R.string.log_start_fetching_from_cloud))
            try {
                val response =
                    firebaseApi.categoriesCollection.orderBy("id").get(Source.SERVER).await()
                val categoriesList = response.toObjects(CategoryData::class.java).toList()
                Timber.tag(tag).d(
                    resProvider.getString(R.string.log_success_fetching_from_cloud,
                        categoriesList.size)
                )
                Result.Success(categoriesList)
            } catch (e: Exception) {
                if (e.localizedMessage.contains(FAILED_TO_GET_DOC)) {
                    Timber.tag(tag).e(resProvider.getString(R.string.log_error_fetching_from_cloud,
                            e.localizedMessage)
                    )
                    Result.Error(resProvider.getString(R.string.error_empty_data_cloud_list))
                } else {
                    Timber.tag(tag).e(resProvider.getString(R.string.log_error_fetching_from_cloud,
                            e.localizedMessage)
                    )
                    Result.Error(e.localizedMessage)
                }
            }
        }
    }
}
