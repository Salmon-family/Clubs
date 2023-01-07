package com.devfalah.entities

data class Comment(
    val id: Int,
    val timeCreated: Long,
    val content: String,
    val totalLikes: Int,
    val isLikedByUser: Boolean,
    val user: User,
    val isMyComment:Boolean,
)