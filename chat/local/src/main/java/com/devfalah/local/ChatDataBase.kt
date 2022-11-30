package com.devfalah.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devfalah.repository.entities.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class ChatDataBase : RoomDatabase(){

    abstract fun chatDao(): ChatDao
}