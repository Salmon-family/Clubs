package com.thechance.viewmodels.conversation.uiStates

data class ChatUIState(
    val appBar: AppBarUIState = AppBarUIState(),
    val messages: List<MessageUIState> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false,
    val error: String? = "",
)

data class MessageUIState(
    val messageId: Int = 0,
    val friendId: Int = 0,
    val isFromUser: Boolean = true,
    var message: String = "",
    val messageDate: String = "",
)

data class AppBarUIState(
    val userName: String = "",
    val icon: String = ""
)