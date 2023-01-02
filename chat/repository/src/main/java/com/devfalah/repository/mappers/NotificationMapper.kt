package com.devfalah.repository.mappers

import com.devfalah.repository.models.NotificationBody
import com.devfalah.repository.models.NotificationDataModel
import com.devfalah.repository.models.NotificationDto
import com.thechance.entities.Notification

fun Notification.toDto(): NotificationDto {
    return NotificationDto(
        data = NotificationDataModel(
            id = this.id,
            friendId = this.friendId,
        ),
        to = this.to,
        notification = NotificationBody(this.title,this.body)
    )
}

