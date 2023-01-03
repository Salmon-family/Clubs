package com.common.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.common.local.daos.ChatDao
import com.common.local.daos.ClubDao
import com.devfalah.repositories.models.PostLocalDto
import com.devfalah.repository.models.ChatLocalDto
import com.devfalah.repository.models.FriendDTOLocal
import com.devfalah.repository.models.MessageEntityLocalDTO

@Database(entities = [PostLocalDto::class, MessageEntityLocalDTO::class, ChatLocalDto::class, FriendDTOLocal::class],
    version = 1)
abstract class ClubDataBase : RoomDatabase() {

    abstract fun clubDao(): ClubDao

    abstract fun chatDao(): ChatDao


}