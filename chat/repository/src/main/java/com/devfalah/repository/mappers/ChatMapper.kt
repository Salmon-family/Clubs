package com.devfalah.repository.mappers

import com.devfalah.repository.models.ChatDTO
import com.devfalah.repository.models.ChatLocalDto
import com.thechance.entities.Chat


fun ChatDTO.toLocalDto(userId: Int): Chat {
    if (messageFrom?.guid == userId) {
        return Chat(
            fullName = messageTo?.fullname ?: "",
            guid = messageTo?.guid ?: 0,
            icon = messageTo?.icon?.larger ?: "",
            time = time ?: 0,
            recentMessage = message ?: ""
        )
    } else {
        return Chat(
            fullName = messageFrom?.fullname ?: "",
            guid = messageFrom?.guid ?: 0,
            icon = messageFrom?.icon?.larger ?: "",
            time = time ?: 0,
            recentMessage = message ?: ""
        )
    }
}

fun ChatLocalDto.toEntity(): Chat {
    return Chat(
        fullName = fullName,
        guid = guid,
        icon = icon,
        time = time,
        recentMessage = recentMessage
    )
}

fun Chat.toLocalDto(): ChatLocalDto {
    return ChatLocalDto(
        fullName = fullName,
        guid = guid,
        icon = icon,
        time = time,
        recentMessage = recentMessage
    )
}