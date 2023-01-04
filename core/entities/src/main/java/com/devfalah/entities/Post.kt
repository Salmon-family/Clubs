package com.devfalah.entities

data class Post(
    val id: Int = 0,
    val privacy: Boolean = false,
    val createdTime: Long = 0L,
    val content: String = "",
    val posterGuid: String = "",
    val imageUrl: String = "",
    val totalLikes: Int = 0,
    val totalComments: Int = 0,
    val publisher: String = "",
    val publisherId: Int = 0,
    val publisherImageUrl: String = "",
    val isLiked: Boolean = false,
    val isSaved: Boolean = false,
    val groupId: Int = 0,
    val groupName: String = "",
    val isMyPost: Boolean = false,
    val isFound: Boolean = true,
)