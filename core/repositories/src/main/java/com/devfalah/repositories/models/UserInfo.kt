package com.devfalah.repositories.models

data class UserInfo(
    val id: Int,
    val name: String,
    val title: String,
    val bio: String,
    val email: String,
    val password: String
)