package com.devfalah.firebase

import com.devfalah.repository.models.NotificationDto
import kotlinx.coroutines.flow.MutableStateFlow

object Events {

     val notification = MutableStateFlow(NotificationDto())
}