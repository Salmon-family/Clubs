package com.thechance.viewmodels.chatWithFriend.states

import java.sql.RowId

data class MessageUIState(
    val userId: Int = 0,
    val friendId: Int = 0,
    var message: String = "",
    val messageDate: Int = 0,
)