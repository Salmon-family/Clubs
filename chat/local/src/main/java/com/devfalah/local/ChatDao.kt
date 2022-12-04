package com.devfalah.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devfalah.repository.entities.MessageEntityLocalDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(chats: List<MessageEntityLocalDTO>)

    @Query("SELECT * FROM MESSAGES_TABLE WHERE friendId = :friendId ORDER BY id DESC")
    fun getMessages(friendId: Int): Flow<List<MessageEntityLocalDTO>>

}