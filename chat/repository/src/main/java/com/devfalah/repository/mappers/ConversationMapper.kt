package com.devfalah.repository.mappers

import com.devfalah.repository.entities.MessageEntityLocalDTO
import com.thechance.entities.Conversation
import com.thechance.entities.Message
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.MessagesDTO
import com.devfalah.repository.models.UserDTO
import com.thechance.entities.User

fun ConversationDTO.toEntity(userId: Int): Conversation {
    return Conversation(
        messages = list?.map { it.toEntity(userId) } ?: emptyList(),
    )
}


fun MessagesDTO.toEntity(userId: Int): Message {
    if(messageFrom?.guid == userId) {
        return Message(
            messageId = id ?: 0,
            friendId = messageTo?.guid ?: 0,
            message = message ?: "",
            time = time ?: 0
        )
    }else{
        return Message(
            messageId = id ?: 0,
            friendId = messageFrom?.guid ?: 0,
            message = message ?: "",
            time = time ?: 0
        )
    }

}

fun UserDTO.toEntity(): User{
    return User(
        userId = guid ?: 0,
        fullName = fullName ?: "",
        icon = icon?.small ?: "",
    )
}

fun MessagesDTO.toMessagesTable(friendId: Int): MessageEntityLocalDTO {
    return MessageEntityLocalDTO(
        id = id ?: 0,
        friendId = friendId,
        message = message ?: "",
        time = time ?: 0,
    )
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