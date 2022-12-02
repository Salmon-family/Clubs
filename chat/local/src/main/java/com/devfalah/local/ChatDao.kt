package com.devfalah.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devfalah.repository.models.ChatTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChats(chats: List<ChatTable>)

    @Query("SELECT * FROM CHATS_TABLE")
    fun getChats(): Flow<List<ChatTable>>
}