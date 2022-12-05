package com.thechance.viewmodels.chatWithFriend.chatWithFriend.uiMappers

import com.thechance.entities.Chat
import com.thechance.viewmodels.chatWithFriend.chatWithFriend.uiState.ChatUiState
import com.thechance.viewmodels.chatWithFriend.extensions.convertLongToTime


fun Chat.toUiState(): ChatUiState {
    return ChatUiState(
        fullName,
        guid,
        icon,
        time.convertLongToTime(),
        recentMessage
    )
}