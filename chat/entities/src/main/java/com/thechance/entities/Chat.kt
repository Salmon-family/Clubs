package com.thechance.entities

data class Chat(
    val fullName: String,
    val guid: Int,
    val icon: String,
    val time: Long,
    val recentMessage: String
)