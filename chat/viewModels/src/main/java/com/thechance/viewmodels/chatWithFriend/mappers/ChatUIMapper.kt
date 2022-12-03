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
        appBar = users.toState()
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
        userId = userId,
        message = message,
        messageDate = time,
    )
}