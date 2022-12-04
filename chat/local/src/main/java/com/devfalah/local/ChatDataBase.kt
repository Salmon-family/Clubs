package com.devfalah.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devfalah.repository.models.ChatLocalDto
import com.devfalah.repository.models.Message

@Database(entities = [Message::class, ChatLocalDto::class], version = 1)
abstract class ChatDataBase : RoomDatabase(){

    abstract fun chatDao(): ChatDao
}