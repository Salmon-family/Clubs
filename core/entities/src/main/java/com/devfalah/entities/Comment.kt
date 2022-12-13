package com.devfalah.entities

data class Comment (
    val id: Int,
    val content: String,
    val userName: String,
    val userImage: String,
    val isDeleted: Boolean,
    val isLikedByUser: Boolean,
    val totalLikes: Int = 0,
    val ownerCommentId: Int,
    val postId: Int,
    val time: String,
    val type: String,
)

data class Success(
    val success: String
)