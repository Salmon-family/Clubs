package com.devfalah.repositories.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CLUB_TABLE")
data class PostLocalDto(
    @PrimaryKey(autoGenerate = false)val id: Int,
    val privacy: Boolean,
    val createdTimeValue: String,
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