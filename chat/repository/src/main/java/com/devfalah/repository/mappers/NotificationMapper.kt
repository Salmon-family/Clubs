package com.devfalah.repository.mappers

import com.devfalah.repository.models.NotificationDataModel
import com.devfalah.repository.models.NotificationDto
import com.thechance.entities.Notification

fun Notification.toDto(): NotificationDto {
    return NotificationDto(
        data = NotificationDataModel(
            id = this.id,
            friendId = this.friendId,
            title = this.title,
            description = this.body,
            clickAction = clickAction
        ),
        to = this.to,
    )
}

