package com.thechance.viewmodels.chats.uiStates

import java.util.Date


data class ChatUiState(
    val fullName: String = "",
    val guid: Int = 0,
    val icon: String = "",
    val time: Long = 0,
    val recentMessage: String = "",
)
