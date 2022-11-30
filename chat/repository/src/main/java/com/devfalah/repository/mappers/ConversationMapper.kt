package com.devfalah.repository.mappers

import com.thechance.entities.Conversation
import com.thechance.entities.Message
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.MessagesDTO

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