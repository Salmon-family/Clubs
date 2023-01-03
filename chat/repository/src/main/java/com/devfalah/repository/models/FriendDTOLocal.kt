package com.devfalah.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FRIENDS")
data class FriendDTOLocal(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val icon: String,
    val title: String
)
