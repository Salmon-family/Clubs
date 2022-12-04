package com.thechance.viewmodels.chatWithFriend.conversation.uiMappers

import com.thechance.entities.Conversation
import com.thechance.entities.Message
import com.thechance.viewmodels.chatWithFriend.conversation.uiStates.ChatUIState
import com.thechance.viewmodels.chatWithFriend.conversation.uiStates.MessageUIState

fun Conversation.toState(): ChatUIState {
    return ChatUIState(
        messages = messages.map { it.toState() },
    )
}

fun Message.toState(): MessageUIState {
    return MessageUIState(
        friendId = friendId,
        message = message,
        messageDate = time,
    )
}

fun Message.toMessage(): MessageUIState {
    return MessageUIState(
        messageId = messageId,
        friendId = friendId,
        message = message,
        messageDate = time,
    )
}