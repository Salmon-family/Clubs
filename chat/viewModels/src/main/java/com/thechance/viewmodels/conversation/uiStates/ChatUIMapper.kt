package com.thechance.viewmodels.conversation.uiMappers

import android.util.Log
import com.thechance.entities.Conversation
import com.thechance.entities.Message
import com.thechance.viewmodels.conversation.uiStates.ChatUIState
import com.thechance.viewmodels.conversation.uiStates.MessageUIState

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
        messageId = id,
        friendId = friendId,
        isFromUser = fromMe,
        message = message,
        messageDate = time,
    )
}