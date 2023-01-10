package com.devfalah.entities

data class NotificationRequest(
    val id: Int,
    val friendId: Int,
    val to: String,
    val title: String,
    val body: String,
    val clickAction: String,
)