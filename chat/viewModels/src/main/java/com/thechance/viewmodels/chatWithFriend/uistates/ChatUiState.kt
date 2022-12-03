package com.thechance.viewmodels.chatWithFriend.uistates


data class ChatUiState(
    val fullName: String = "",
    val guid: Int = 0,
    val icon: String = "",
    val time: String = "",
    val recentMessage: String = ""
)
