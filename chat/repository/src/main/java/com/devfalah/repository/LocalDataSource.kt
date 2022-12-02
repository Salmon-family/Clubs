package com.devfalah.repository

import com.devfalah.repository.models.ChatEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertChats(chats: List<ChatEntity>)

    fun getChats(): Flow<List<ChatEntity>>
}