package com.thechance.identity.entities

data class UserData(
    val fullName: String,
    val jobTitle: String,
    val fcmToken: String,
    val email: String,
    val reEmail: String,
    val gender: String,
    val birthdate: String,
    val username: String,
    val password: String,
)