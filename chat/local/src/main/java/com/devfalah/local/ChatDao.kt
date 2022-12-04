package com.devfalah.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devfalah.repository.models.MessageEntityLocalDTO
import com.devfalah.repository.models.ChatLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChats(chats: List<ChatLocalDto>)

    @Query("SELECT * FROM CHATS_TABLE ORDER BY time ASC")
    fun getChats(): Flow<List<ChatLocalDto>>

    @Query("SELECT * FROM CHATS_TABLE WHERE fullName LIKE '%' || :query || '%' ORDER BY time ASC")
    fun getChats(query: String): Flow<List<ChatLocalDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(chats: List<MessageEntityLocalDTO>)

    @Query("SELECT * FROM MESSAGES_TABLE WHERE friendId = :friendId ORDER BY id DESC")
    fun getMessages(friendId: Int): Flow<List<MessageEntityLocalDTO>>

}