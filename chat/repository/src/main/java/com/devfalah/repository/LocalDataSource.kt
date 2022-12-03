package com.devfalah.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devfalah.repository.entities.MessageEntity
import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertMessages(messages: List<MessageEntity>)

    fun getMessages(): Flow<List<MessageEntity>>

}