package com.devfalah.firebase

import com.devfalah.repository.ChatFirebaseDataSource
import com.devfalah.repository.models.NotificationDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatFirebaseDataSourceImp @Inject constructor() : ChatFirebaseDataSource {

    override fun onReceiveNotification(): Flow<NotificationDataModel> {
        return FirebaseCloudMessagingService.events
    }

    override suspend fun getToken(): Flow<String> {
        return FirebaseCloudMessagingService.newToken
    }

}