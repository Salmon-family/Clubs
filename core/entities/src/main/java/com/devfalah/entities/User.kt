package com.devfalah.entities

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val birthdate: String,
    val email: String,
    val title: String,
    val bio: String,
    val gender: String,
    val profileUrl: String,
    val coverUrl: String,
    val language: String,
    val isMyProfile:Boolean,
    val isFriend: Boolean,
    val isRequestExists: Boolean
)

