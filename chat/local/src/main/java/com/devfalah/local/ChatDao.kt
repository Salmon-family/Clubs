package com.devfalah.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devfalah.repository.entities.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(chats: List<MessageEntity>)

    @Query("SELECT * FROM MESSAGES_TABLE")
    fun getMessages(): Flow<List<MessageEntity>>

}