package com.thechance.entities


data class User(
    val id: Int,
    val name: String,
    val profileUrl: String,
    val fcmToken: String,
)