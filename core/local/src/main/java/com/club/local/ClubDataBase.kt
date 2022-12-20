package com.club.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devfalah.repositories.models.PostLocalDto

@Database(entities = [PostLocalDto::class], version = 1)
abstract class ClubDataBase : RoomDatabase() {

    abstract fun clubDao(): ClubDao


}


