package com.devfalah.repositories

interface FirebaseCloudMessagingDataSource {
    suspend fun pushNotification(notificationRequestBody: NotificationRequestBody): Boolean
}