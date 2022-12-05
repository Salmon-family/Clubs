package com.thechance.viewmodels.chatWithFriend.uiStates

data class ChatsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val chats: List<ChatUiState> = emptyList(),
    val searchText : String = "",
)