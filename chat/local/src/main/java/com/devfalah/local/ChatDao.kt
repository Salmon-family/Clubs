package com.devfalah.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devfalah.repository.models.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChats(chats: List<ChatEntity>)

    @Query("SELECT * FROM CHATS_TABLE ORDER BY time ASC")
    fun getChats(): Flow<List<ChatEntity>>

    @Query("SELECT * FROM CHATS_TABLE WHERE fullName LIKE '%' || :query || '%' ORDER BY fullName ASC")
    fun getChats(query: String): Flow<List<ChatEntity>>
}