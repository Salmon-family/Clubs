package com.thechance.viewmodels.chatWithFriend.mappers

import com.thechance.entities.Conversation
import com.thechance.entities.Message
import com.thechance.entities.User
import com.thechance.viewmodels.chatWithFriend.states.AppBarUIState
import com.thechance.viewmodels.chatWithFriend.states.ChatUIState
import com.thechance.viewmodels.chatWithFriend.states.MessageUIState

fun Conversation.toState(): ChatUIState {
    return ChatUIState(
        messages = messages.map { it.toState() },
    )
}

fun User.toState(): AppBarUIState {
    return AppBarUIState(
        id = userId,
        userName = fullName,
        icon = icon
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