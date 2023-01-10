package com.common.remote

import com.devfalah.repositories.FirebaseCloudMessagingDataSource
import com.devfalah.repositories.NotificationRequestBody
import javax.inject.Inject

class FirebaseCloudMessagingDataSourceImp @Inject constructor(
    private val cloudMessagingService: FirebaseCloudMessagingService
): FirebaseCloudMessagingDataSource {


    override suspend fun pushNotification(notificationRequestBody: NotificationRequestBody): Boolean {
        return cloudMessagingService.postNotification(notificationRequestBody).isSuccessful
    }
}