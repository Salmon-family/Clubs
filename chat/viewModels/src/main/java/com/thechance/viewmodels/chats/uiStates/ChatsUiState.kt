package com.thechance.viewmodels.chats.uiStates

data class ChatsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val chats: List<ChatUiState> = emptyList(),
    val chatsCount: Int = 0,
    val searchText: String = "",
    val isLoadingMore: Boolean = false,
    val isLastPage: Boolean = false,
)


fun ChatsUiState.isEmpty() = chats.isEmpty()