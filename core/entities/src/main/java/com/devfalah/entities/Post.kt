package com.devfalah.entities

data class Post(
    val postID: Int,
    val privacy: Boolean,
    val createdTime: String,
    val content: String,
    val image: String,
    val totalLikes: Int,
    val totalComments: Int,
    val posterName: String,
    val posterID: Int,
    val posterImage: String,
    val isLikedByUser: Boolean
)