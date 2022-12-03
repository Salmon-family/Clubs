package com.devfalah.repository.mappers

import com.devfalah.repository.models.*
import com.thechance.entities.Conversation
import com.thechance.entities.Chat

fun ConversationDTO.toEntity(userId: Int): Conversation {
    return Conversation(
        chats = list?.map { it.toEntity(userId) } ?: emptyList()
    )
}

fun MessagesDTO.toLocalModel(userId: Int): ChatEntity {
    if (messageFrom?.guid == userId) {
        return ChatEntity(
            fullName = messageTo?.fullname ?: "",
            guid = messageTo?.guid ?: 0,
            icon = messageTo?.icon?.small ?: "",
            time = time ?: 0,
            recentMessage = message ?: ""
        )
    } else {
        return ChatEntity(
            fullName = messageFrom?.fullname ?: "",
            guid = messageFrom?.guid ?: 0,
            icon = messageFrom?.icon?.small ?: "",
            time = time ?: 0,
            recentMessage = message ?: ""
        )
    }
}

fun MessagesDTO.toEntity(userId: Int): Chat {
    if (messageFrom?.guid == userId) {
        return Chat(
            fullName = messageTo?.fullname ?: "",
            guid = messageTo?.guid ?: 0,
            icon = messageTo?.icon?.small ?: "",
            time = time ?: 0,
            recentMessage = message ?: ""
        )
    } else {
        return Chat(
            fullName = messageFrom?.fullname ?: "",
            guid = messageFrom?.guid ?: 0,
            icon = messageFrom?.icon?.small ?: "",
            time = time ?: 0,
            recentMessage = message ?: ""
        )
    }
}

fun ChatEntity.toEntity(): Chat {
    return Chat(
        fullName = fullName,
        guid = guid,
        icon = icon,
        time = time,
        recentMessage = recentMessage
    )
}

fun Chat.toLocalModel(): ChatEntity {
    return ChatEntity(
        fullName = fullName,
        guid = guid,
        icon = icon,
        time = time,
        recentMessage = recentMessage
    )
}