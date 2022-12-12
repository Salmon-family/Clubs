package com.thechance.viewmodels.chats.uiStates

data class ChatsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val chats: List<ChatUiState> = emptyList(),
    val searchText: String = "",
)