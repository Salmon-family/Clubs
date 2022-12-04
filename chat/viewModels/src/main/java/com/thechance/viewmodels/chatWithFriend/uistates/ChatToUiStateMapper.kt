package com.thechance.viewmodels.chatWithFriend.uistates

import com.thechance.entities.Chat
import com.thechance.viewmodels.extensions.convertLongToTime
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState

fun Chat.toUiState(): ChatUiState {
    return ChatUiState(
        fullName,
        guid,
        icon,
        time.convertLongToTime(),
        recentMessage
    )
}