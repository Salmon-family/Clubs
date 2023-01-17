package com.devfalah.entities

data class Members(
    val friends: List<User>,
    val total: Int,
    val page: Int,
    val isMyFriends: Boolean = false
)