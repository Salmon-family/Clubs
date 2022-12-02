package com.devfalah.repository.mappers

import com.devfalah.repository.models.*
import com.thechance.entities.Conversation
import com.thechance.entities.Chat

fun ConversationDTO.toEntity(userId: Int): Conversation {
    return Conversation(
        chats = list?.map { it.toEntity(userId) } ?: emptyList()
    )
}

fun MessagesDTO.toChatTable(userId: Int): ChatTable {
    if (messageFrom?.let { checkSender(it, userId) } == Sender.ME) {
        return ChatTable(
            fullName = messageTo?.fullname ?: "",
            guid = messageTo?.guid ?: 0,
            icon = messageTo?.icon?.small ?: "",
            time = time ?: 0,
            recentMessage = message ?: ""
        )
    } else {
        return ChatTable(
            fullName = messageFrom?.fullname ?: "",
            guid = messageFrom?.guid ?: 0,
            icon = messageFrom?.icon?.small ?: "",
            time = time ?: 0,
            recentMessage = message ?: ""
        )
    }
}

fun MessagesDTO.toEntity(userId: Int): Chat {
    if (messageFrom?.let { checkSender(it, userId) } == Sender.ME) {
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

fun ChatTable.toChat(): Chat {
    return Chat(
        fullName = fullName,
        guid = guid,
        icon = icon,
        time = time,
        recentMessage = recentMessage
    )
}

private fun checkSender(messageFrom: MessageDTO, userId: Int) : Sender {
    return if (messageFrom.guid == userId) {
        Sender.ME
    } else {
        Sender.FRIEND
    }
}
enum class Sender {
    ME,
    FRIEND
}