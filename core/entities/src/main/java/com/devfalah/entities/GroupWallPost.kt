package com.devfalah.entities

data class GroupWallPost(
    val friends: List<Any>,
    val location: String,
    val post: Post,
    val text: String,
    val user: User
)
