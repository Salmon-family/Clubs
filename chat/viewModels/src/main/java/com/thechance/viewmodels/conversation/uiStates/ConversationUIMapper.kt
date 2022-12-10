package com.thechance.viewmodels.conversation.uiStates

import com.thechance.entities.Message

fun Message.toUiState(): MessageUIState {
    return MessageUIState(
        messageId = id,
        friendId = friendId,
        isFromUser = fromMe,
        message = message,
        messageDate = time,
    )
}