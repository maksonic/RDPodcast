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
    suspend fun getCloudCategories(): CategoriesData

    @ExperimentalCoroutinesApi
    class Base @Inject constructor(
        private val firebaseApi: FirebaseApi,
        private val resourceProvider: ResourceProvider,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : CategoriesCloudDataSource {
        companion object {
            const val TAG = "CategoriesCloudDataSource"
        }

        override suspend fun getCloudCategories(): CategoriesData = withContext(dispatcher) {
            Timber.tag(TAG).i("FETCH FROM CLOUD")
            try {
                val response =
                    firebaseApi.categoriesCollection.orderBy("id").get(Source.SERVER).await()
                if (response.isEmpty) {
                    Timber.tag(TAG).e("|-| Empty Fetched list ${response.count()}")
                    Result.Error(resourceProvider.getString(R.string.error_empty_categories_list))
                } else {
                    Timber.tag(TAG).d("|+| Fetched ${response?.count()} categories")
                    val categories = response.toObjects(CategoryData::class.java).toList()
                    Result.Success(categories)
                }
            } catch (e: Exception) {
                if (e.localizedMessage.contains(FAILED_TO_GET_DOC)) {
                    Timber.tag(TAG).e("|-| $FAILED_TO_GET_DOC")
                    Result.Error(resourceProvider.getString(R.string.error_empty_categories_list))
                } else {
                    Timber.tag(TAG).e("|-| ${e.localizedMessage}")
                    Result.Error(e.localizedMessage)
                }
            }
        }
    }
}