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

fun NotificationDto.toMessage(): Message {
    return Message(
        id = this.data.id,
        friendId = this.data.friendId,
        fromMe = false,
        time = this.data.time,
        message = this.data.messageText
    )
}
