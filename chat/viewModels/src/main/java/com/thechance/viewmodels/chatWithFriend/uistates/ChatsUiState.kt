package com.thechance.viewmodels.chatWithFriend.uistates

data class ChatsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val chats: List<ChatUiState> = emptyList(),
)