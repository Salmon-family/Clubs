package com.devfalah.repository.mappers

import com.devfalah.repository.models.ChatDTO
import com.devfalah.repository.models.MessageEntityLocalDTO
import com.thechance.entities.Message


fun ChatDTO.toEntity(userId: Int): Message {
    return if (messageFrom?.guid == userId) {
        Message(
            id = id ?: 0,
            friendId = messageTo?.guid ?: 0,
            fromMe = true,
            message = message ?: "",
            time = time?:0,
        )
    } else {
        Message(
            id = id ?: 0,
            friendId = messageFrom?.guid ?: 0,
            fromMe = false,
            message = message ?: "",
            time = time?:0,
        )
    }

}

fun MessageEntityLocalDTO.toMessage(): Message {
    return Message(
        id = id,
        friendId = friendId,
        fromMe = fromMe,
        message = message,
        time = time.toLong(),
    )
}

fun Message.toMessage(): MessageEntityLocalDTO {
    return MessageEntityLocalDTO(
        id = id,
        friendId = friendId,
        fromMe = fromMe,
        message = message,
        time = time.toString(),
    )
}