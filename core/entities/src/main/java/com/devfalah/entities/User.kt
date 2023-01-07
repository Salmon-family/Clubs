package com.devfalah.entities

data class User(
    val id: Int = 0,
    val name: String = "",
    val username: String = "",
    val birthdate: String = "",
    val email: String = "",
    val title: String = "",
    val gender: String = "",
    val profileUrl: String = "",
    val coverUrl: String = "",
    val language: String = "",
    val isMyProfile: Boolean = false,
    val isFriend: Boolean = false,
    val isRequestExists: Boolean = false
)

