package com.thechance.identity.entities

data class UserData(
    val fullName: String = "",
    val jobTitle: String = "",
    val fcmToken: String = "",
    val email: String = "",
    val reEmail: String = "",
    val gender: String = "",
    val birthdate: String = "",
    val username: String = "",
    val password: String = "",
    val userId: Int = 0,
    val newEmail: String = "",
    val newGender: String = "",
    val currentPassword: String = "",
    val newFullName: String = "",
    val newFcmToken: String = "",
    val newJobTitle: String = ""
)