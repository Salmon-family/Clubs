package com.devfalah.firebase

import com.devfalah.repositories.CoreFireStoreDataSource
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CoreFireStoreDataSourceImpl @Inject constructor(
    private val firebaseFireStore: FirebaseFirestore
) : CoreFireStoreDataSource {

    override suspend fun addBugReport(
        userId: Int,
        message: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        val bugMessage: MutableMap<String, Any> = HashMap()
        bugMessage["userId"] = userId
        bugMessage["message"] = message

        firebaseFireStore
            .collection("bugMessages")
            .add(bugMessage)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFail(it) }
    }

}