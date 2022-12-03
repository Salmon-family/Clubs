package com.devfalah.repository.mappers

import com.devfalah.repository.entities.MessageEntity
import com.thechance.entities.Conversation
import com.thechance.entities.Message
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.MessageDTO
import com.devfalah.repository.models.MessagesDTO
import com.devfalah.repository.models.UserDTO
import com.thechance.entities.User

fun ConversationDTO.toEntity(): Conversation {
    return Conversation(
        messages = list?.map { it.toEntity() } ?: emptyList(),
        users = withuser?.toEntity() ?: User()
    )
}


fun MessagesDTO.toEntity(): Message {
    return Message(
        messageId = id ?: 0,
        userId = messageFrom?.guid ?: 0,
        message = message ?: "",
        time = time ?: 0
    )
}

fun UserDTO.toEntity(): User{
    return User(
        userId = guid ?: 0,
        fullName = fullName ?: "",
        icon = icon?.small ?: "",
    )
}

fun MessagesDTO.toMessagesTable(userId: Int, friendId: Int): MessageEntity {
        return MessageEntity(
            id = id ?: 0,
            message = message ?: "",
            time = time ?: 0,
        )
}

fun MessageEntity.toMessage(): Message {
    return Message(
        messageId = id,
        message = message,
        time = time,
    )
}

fun Message.toMessage(): MessageEntity {
    return MessageEntity(
        id = messageId,
        message = message,
        time = time,
    )
}