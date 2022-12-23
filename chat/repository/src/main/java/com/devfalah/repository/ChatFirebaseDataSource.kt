package com.devfalah.repository

import com.devfalah.repository.models.NotificationDataModel
import com.devfalah.repository.models.NotificationDto
import kotlinx.coroutines.flow.Flow

interface ChatFirebaseDataSource {

    fun onReceiveNotification(): Flow<NotificationDataModel>

    suspend fun getToken(): Flow<String>
}