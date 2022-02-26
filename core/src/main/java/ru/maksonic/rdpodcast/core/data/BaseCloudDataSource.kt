package ru.maksonic.rdpodcast.core.data

import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.maksonic.rdpodcast.core.R
import ru.maksonic.rdpodcast.core.ResourceProvider
import ru.maksonic.rdpodcast.core.Result
import ru.maksonic.rdpodcast.core.Abstract
import ru.maksonic.rdpodcast.core.logging.classTag
import timber.log.Timber
import java.lang.Exception

/**
 * @Author: maksonic on 12.02.2022
 */
typealias BaseCloudDataSource<D> = CloudDataSource.Base<D>

interface CloudDataSource<D> {
    suspend fun fetchCloudData(vararg data: Any?): Result<List<D>>

    abstract class Base<D : Abstract.DataObject>(
        private val res: ResourceProvider,
        private val networkException: NetworkException,
        private val dispatcher: CoroutineDispatcher
    ) : CloudDataSource<D> {
        private val tag = classTag()

        override suspend fun fetchCloudData(vararg data: Any?): Result<List<D>> =
            withContext(dispatcher) {
                Timber.tag(tag).i(res.getString(R.string.log_start_fetching_from_cloud))
                try {
                    response(*data)
                    if (response(*data).documents.isEmpty()) {
                        Timber.tag(tag).e(res.getString(R.string.log_error_empty_cloud_list))
                        Result.Error(res.getString(R.string.error_empty_data_cloud_list))
                    } else {
                        Timber.tag(tag).d(res.getString(R.string.log_success_fetching_from_cloud,
                            dataResult(*data).size))
                        Result.Success(dataResult(*data))
                    }
                } catch (e: Exception) {
                    if (e.localizedMessage.contains(networkException.firebaseFailedToGetDoc())) {
                        Timber.tag(tag).e(res.getString(R.string.log_error_fetching_from_cloud,
                            e.localizedMessage))
                        Result.Error(res.getString(R.string.error_empty_data_cloud_list))
                    } else {
                        Timber.tag(tag).e(res.getString(R.string.log_error_fetching_from_cloud,
                            e.localizedMessage))
                        Result.Error(e.localizedMessage.toString())
                    }
                }
            }

        protected abstract suspend fun response(vararg data: Any?): QuerySnapshot
        protected abstract suspend fun dataResult(vararg data: Any?): List<D>
    }
}
