package com.thechance.viewmodels.chatWithFriend.conversation.uiMappers

import android.util.Log
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
    Log.i("TAG", "toMessage: $fromMe")
    return MessageUIState(
        messageId = id,
        friendId = friendId,
        isFromUser = fromMe,
        message = message,
        messageDate = time,
    )
}