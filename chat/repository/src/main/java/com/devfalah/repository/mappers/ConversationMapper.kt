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
    if(messageFrom?.guid == userId) {
        return Message(
            messageId = id ?: 0,
            friendId = messageTo?.guid ?: 0,
            message = message ?: "",
            time = time?.let { ConvertDate().convertTime(it) } ?: ""
        )
    }else{
        return Message(
            messageId = id ?: 0,
            friendId = messageFrom?.guid ?: 0,
            message = message ?: "",
            time = time?.let { ConvertDate().convertTime(it) } ?: ""
        )
    }

}

fun MessageEntityLocalDTO.toMessage(): Message {
    return Message(
        messageId = id,
        friendId = friendId,
        message = message,
        time = time,
    )
}

fun Message.toMessage(): MessageEntityLocalDTO {
    return MessageEntityLocalDTO(
        id = messageId,
        friendId = friendId,
        message = message,
        time = time,
    )
}