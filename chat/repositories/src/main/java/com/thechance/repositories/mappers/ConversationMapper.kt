package com.thechance.repositories.mappers

import com.thechance.entities.Conversation
import com.thechance.entities.Message
import com.thechance.repositories.models.ConversationDTO
import com.thechance.repositories.models.MessagesDTO

fun ConversationDTO.toEntity(): Conversation {
    return Conversation(
        messages = list?.map { it.toEntity() } ?: emptyList()
    )
}


fun MessagesDTO.toEntity(): Message {
    return Message(
        messageID = id ?: 0,
        message = message ?: "",
    )
}