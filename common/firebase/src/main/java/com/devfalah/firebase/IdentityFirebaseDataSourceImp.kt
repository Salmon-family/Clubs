package com.devfalah.firebase

import com.thechance.identity.repositories.IdentityFirebaseDataSource
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class IdentityFirebaseDataSourceImp @Inject constructor() : IdentityFirebaseDataSource {
    override suspend fun getToken(): String {

        return FirebaseCloudMessagingService.newToken.filter { it.isNotEmpty() }.first()
    }
}