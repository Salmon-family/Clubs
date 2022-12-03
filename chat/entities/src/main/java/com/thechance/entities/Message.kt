package com.thechance.entities

data class Message(
    val messageId: Int,
    val userId: Int = 0,
    val message: String,
    val time: Int,
)