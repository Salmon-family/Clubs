package com.thechance.viewmodels.chatWithFriend.chatWithFriend.uiState

data class ChatsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val chats: List<ChatUiState> = emptyList(),
    val searchText : String = "",
)

data class ChatUiState(
    val fullName: String = "",
    val guid: Int = 0,
    val icon: String = "",
    val time: String = "",
    val recentMessage: String = ""
)
