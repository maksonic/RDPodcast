package ru.maksonic.rdpodcast.core.data

import javax.inject.Inject

/**
 * @Author: maksonic on 07.02.2022
 */
interface NetworkException {
    fun firebaseFailedToGetDoc(): String

    class Base @Inject constructor() : NetworkException {

        override fun firebaseFailedToGetDoc() = FAILED_TO_GET_DOC

        private companion object {
            private const val FAILED_TO_GET_DOC =
                "Failed to get documents from server. (However, these documents may exist in the local cache. Run again without setting source to SERVER to retrieve the cached documents.)"
        }
    }
}