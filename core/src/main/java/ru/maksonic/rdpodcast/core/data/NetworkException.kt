package ru.maksonic.rdpodcast.core.data

/**
 * @Author: maksonic on 07.02.2022
 */
object NetworkException {
    const val FAILED_TO_GET_DOC =
        "Failed to get documents from server. (However, these documents may exist in the local cache. Run again without setting source to SERVER to retrieve the cached documents.)"
}