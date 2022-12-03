package com.thechance.viewmodels.chatWithFriend.states


data class MessageUIState(
    val messageId: Int = 0,
    val friendId: Int = 0,
    var message: String = "",
    val messageDate: Int = 0,
)