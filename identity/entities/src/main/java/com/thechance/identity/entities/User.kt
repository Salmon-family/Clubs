package com.thechance.identity.entities

data class User(
    val birthdate: String,
    val coverUrl: Boolean,
    val email: String,
    val fullName: String,
    val gender: String,
    val guid: Int,
    val language: String,
    val jobTitle: String,
    val fcmToken: String,
    val username: String,
)
