package com.thechance.entities

data class Message(
    val id: Int,
    val friendId: Int,
    val fromMe: Boolean,
    val message: String,
    val time: Long,
)