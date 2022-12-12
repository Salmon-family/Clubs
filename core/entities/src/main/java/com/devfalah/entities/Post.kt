package com.devfalah.entities

data class Post(
    val id: Int,
    val privacy: Boolean,
    val createdTime: Long,
    val content: String,
    val imageUrl: String,
    val totalLikes: Int,
    val totalComments: Int,
    val publisher: String,
    val publisherId: Int,
    val publisherImageUrl: String,
    val isLiked: Boolean
)