package com.thechance.entities

data class Notification (
    val id: Int,
    val friendId: Int,
    val messageText: String,
    val time: String,
    val to: String,
)