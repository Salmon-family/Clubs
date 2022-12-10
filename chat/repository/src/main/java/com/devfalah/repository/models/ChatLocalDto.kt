package com.devfalah.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CHATS_TABLE")
data class ChatLocalDto(
    @PrimaryKey(autoGenerate = false) val guid: Int,
    val fullName: String,
    val icon: String,
    val time: Long,
    val recentMessage: String,
)