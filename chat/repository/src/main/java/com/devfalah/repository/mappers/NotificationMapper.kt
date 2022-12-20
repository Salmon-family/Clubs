package com.devfalah.repository.mappers

import com.devfalah.repository.models.NotificationDataModel
import com.devfalah.repository.models.NotificationDto
import com.thechance.entities.Message
import com.thechance.entities.Notification

fun Notification.toDto(): NotificationDto {
    return NotificationDto(
        data = NotificationDataModel(
            id = this.id,
            friendId = this.friendId,
            messageText = this.messageText,
            time = this.time),
        to = this.to
    )
}

fun NotificationDataModel.toMessage(): Message {
    return Message(
        id = this.id,
        friendId = this.friendId,
        fromMe = false,
        time = this.time.toLong(),
        message = this.messageText
    )
}
