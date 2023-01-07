package com.thechance.entities

data class Friends(
    val friends: List<Friend>,
    val total: Int,
    val page: Int,
)