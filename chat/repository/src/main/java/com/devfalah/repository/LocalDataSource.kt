package com.devfalah.repository

import com.devfalah.repository.models.ChatTable
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertChats(chats: List<ChatTable>)

    fun getChats(): Flow<List<ChatTable>>
}