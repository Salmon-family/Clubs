package com.devfalah.entities

data class NotificationRequest(
    val id: Int = 0,
    val friendId: Int = 0,
    val to: String,
    val title: String = "",
    val body: String = "",
    val clickAction: String,
)