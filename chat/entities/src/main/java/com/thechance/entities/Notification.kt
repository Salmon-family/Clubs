package com.thechance.entities

data class Notification (
    val id: Int,
    val friendId: Int,
    val to: String,
    val title: String,
    val body: String,
)