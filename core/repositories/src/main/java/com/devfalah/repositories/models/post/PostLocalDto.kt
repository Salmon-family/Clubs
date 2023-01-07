package com.devfalah.repositories.models.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CLUB_TABLE")
data class PostLocalDto(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val groupId: Int,
    val groupName: String,
    val privacy: Boolean,
    val createdTime: Long,
    val content: String,
    val imageUrl: String,
    val totalLikes: Int,
    val totalComments: Int,
    val publisher: String,
    val publisherId: Int,
    val publisherImageUrl: String,
    val isLiked: Boolean,
    val isFromAlbum: Boolean
)

@Entity(tableName = "HOME_TABLE")
data class PostHomeDto(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val privacy: Boolean,
    val createdTime: Long,
    val content: String,
    val imageUrl: String,
    val totalLikes: Int,
    val totalComments: Int,
    val publisher: String,
    val publisherId: Int,
    val publisherImageUrl: String,
    val isLiked: Boolean,
    val isFromAlbum: Boolean
)

data class PostHome(
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
    val isLiked: Boolean,
    val isFromAlbum: Boolean,
    val saved: Boolean,
)