package com.devfalah.repository.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MESSAGES_TABLE")
data class MessageEntity (
    @PrimaryKey val id:Int,
    val message: String,
    val time: Int,
)