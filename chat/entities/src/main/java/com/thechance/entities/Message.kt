package com.thechance.entities

data class Message(
    val messageId: Int,
    val friendId: Int,
    val message: String,
    val time: Int,
)