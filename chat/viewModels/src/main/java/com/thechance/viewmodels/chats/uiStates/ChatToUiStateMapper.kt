package com.thechance.viewmodels.chats.uiStates

import com.thechance.entities.Chat


fun Chat.toUiState(): ChatUiState {
    return ChatUiState(
        fullName,
        guid,
        icon,
        time,
        recentMessage
    )
}

