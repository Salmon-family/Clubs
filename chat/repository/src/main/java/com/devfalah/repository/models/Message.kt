package com.devfalah.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MESSAGES_TABLE")
data class Message (
    @PrimaryKey val id:Int,
        )