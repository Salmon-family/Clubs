package com.thechance.viewmodels.conversation.uiStates

data class MessageUIState(
    val messageId: Int = 0,
    val friendId: Int = 0,
    val isFromUser: Boolean = true,
    val message: String = "",
    val messageDate: Long = 0,
)