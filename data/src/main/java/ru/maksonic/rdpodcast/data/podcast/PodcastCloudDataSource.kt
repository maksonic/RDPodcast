package ru.maksonic.rdpodcast.data.podcast

import com.google.firebase.firestore.Source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.data.NetworkException
import ru.maksonic.rdpodcast.core.di.IoDispatcher
import ru.maksonic.rdpodcast.core.logging.classTag
import ru.maksonic.rdpodcast.data.FirebaseApi
import ru.maksonic.rdpodcast.data.R
import timber.log.Timber
import javax.inject.Inject

/**
 * @Author: maksonic on 10.02.2022
 */
interface PodcastCloudDataSource {
    val tag: String
    suspend fun getCloudPodcast(category: String?): Result<List<PodcastData>>

    @ExperimentalCoroutinesApi
    class Base @Inject constructor(
        private val firebaseApi: FirebaseApi,
        private val resProvider: ResourceProvider,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : PodcastCloudDataSource {
        override val tag = this.classTag()

        override suspend fun getCloudPodcast(category: String?): Result<List<PodcastData>> =
            withContext(dispatcher) {
                Timber.tag(tag).i(resProvider.getString(R.string.log_start_fetching_from_cloud))
                try {
                    if (category != null) {
                        val response =
                            firebaseApi.categoriesCollection.document(category)
                                .collection(firebaseApi.podcastCollection).get(Source.SERVER)
                                .await()

                        val podcastList = response.toObjects(PodcastData::class.java).toList()
                        Timber.tag(tag).d(resProvider
                            .getString(R.string.log_success_fetching_from_cloud, podcastList.size)
                        )
                        Result.Success(podcastList)
                    } else {
                        Timber.tag(tag).e(resProvider
                            .getString(R.string.log_error_category_not_found)
                        )
                        Result.Error(resProvider.getString(R.string.error_empty_category))
                    }
                } catch (e: Exception) {
                    if (e.localizedMessage.contains(NetworkException.FAILED_TO_GET_DOC)) {
                        Timber.tag(tag).e(resProvider
                            .getString(R.string.log_error_fetching_from_cloud, e.localizedMessage)
                        )
                        Result.Error(resProvider.getString(R.string.error_empty_data_cloud_list))
                    } else {
                        Timber.tag(tag).e(resProvider
                            .getString(R.string.log_error_fetching_from_cloud, e.localizedMessage)
                        )
                        Result.Error(e.localizedMessage)
                    }
                }
            }
    }
}