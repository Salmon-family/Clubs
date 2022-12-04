package com.devfalah.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MESSAGES_TABLE")
data class MessageEntityLocalDTO (
    @PrimaryKey val id:Int,
    val friendId: Int,
    val message: String,
    val time: String,
)