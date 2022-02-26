package ru.maksonic.rdpodcast.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

/**
 * @Author: maksonic on 07.02.2022
 */
interface FirebaseApi {
    val firestoreInstance: FirebaseFirestore
    val categoriesCollection: CollectionReference
    val podcastCollectionName: String
    fun categoryCollectionName(): String

    class Base : FirebaseApi {
        override val firestoreInstance = FirebaseFirestore.getInstance()
        override val categoriesCollection = firestoreInstance.collection(CATEGORIES)

        override fun categoryCollectionName(): String {
            return CATEGORIES
        }

        override val podcastCollectionName = PODCAST_LIST

        private companion object {
            private const val CATEGORIES = "categories"
            private const val PODCAST_LIST = "podcast_list"
        }
    }
}