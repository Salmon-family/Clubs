package com.devfalah.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devfalah.repository.models.ChatTable
import com.devfalah.repository.models.Message

@Database(entities = [Message::class, ChatTable::class], version = 1)
abstract class ChatDataBase : RoomDatabase(){

    abstract fun chatDao(): ChatDao
}