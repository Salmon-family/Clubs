package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun insertChatsLocally(list: List<Chat>)

    fun getChatsFromLocal(): Flow<List<Chat>>

    suspend fun getChats(userID: Int): List<Chat>

    fun getChats(query: String): Flow<List<Chat>>
}