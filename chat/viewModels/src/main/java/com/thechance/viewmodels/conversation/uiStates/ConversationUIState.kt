package com.thechance.viewmodels.conversation.uiStates

data class ConversationUIState(
    val appBar: AppBarUIState = AppBarUIState(),
    val messages: List<MessageUIState> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false,
    val error: String? = "",
    val isLastPage: Boolean = false,
    val messagesCount: Int = 0,
    val isLoadingMore: Boolean = false,
)


