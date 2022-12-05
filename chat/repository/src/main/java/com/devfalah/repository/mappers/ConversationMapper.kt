package com.devfalah.repository.mappers

import com.devfalah.repository.ConvertDate
import com.devfalah.repository.models.MessageEntityLocalDTO
import com.thechance.entities.Conversation
import com.thechance.entities.Message
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.ChatDTO

fun ConversationDTO.toEntity(userId: Int): Conversation {
    return Conversation(
        messages = list?.map { it.toEntity(userId) } ?: emptyList(),
    )
}


fun ChatDTO.toEntity(userId: Int): Message {
    return if(messageFrom?.guid == userId) {
        Message(
            id = id ?: 0,
            friendId = messageTo?.guid ?: 0,
            fromMe = true,
            message = message ?: "",
            time = time?.let { ConvertDate().convertTime(it) } ?: ""
        )
    }else{
        Message(
            id = id ?: 0,
            friendId = messageFrom?.guid ?: 0,
            fromMe = false,
            message = message ?: "",
            time = time?.let { ConvertDate().convertTime(it) } ?: ""
        )
    }

}

fun MessageEntityLocalDTO.toMessage(): Message {
    return Message(
        id = id,
        friendId = friendId,
        fromMe = fromMe,
        message = message,
        time = time,
    )
}

fun Message.toMessage(): MessageEntityLocalDTO {
    return MessageEntityLocalDTO(
        id = id,
        friendId = friendId,
        fromMe = fromMe,
        message = message,
        time = time,
    )
}