package com.thechance.viewmodels.chatWithFriend.uiStates

import com.thechance.entities.Chat
import com.thechance.viewmodels.extensions.convertLongToTime

fun Chat.toUiState(): ChatUiState {
    return ChatUiState(
        fullName,
        guid,
        icon,
        time.convertLongToTime(),
        recentMessage
    )
}