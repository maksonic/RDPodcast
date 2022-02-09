package ru.maksonic.rdpodcast.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ru.maksonic.rdpodcast.data.DbCollection.CATEGORIES

/**
 * @Author: maksonic on 07.02.2022
 */
interface FirebaseApi {

    val firestoreInstance: FirebaseFirestore
    val categoriesCollection: CollectionReference

    class Base : FirebaseApi {
        override val firestoreInstance = FirebaseFirestore.getInstance()
        override val categoriesCollection = firestoreInstance.collection(CATEGORIES)
    }
}