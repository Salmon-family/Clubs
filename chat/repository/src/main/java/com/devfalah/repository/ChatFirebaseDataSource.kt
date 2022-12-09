package com.devfalah.repository

import com.devfalah.repository.models.NotificationDto
import kotlinx.coroutines.flow.Flow

interface ChatFirebaseDataSource {

    fun onReceiveNotification(): Flow<NotificationDto>
}