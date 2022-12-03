package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import com.thechance.entities.Conversation
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): Conversation

    suspend fun insertChatsLocally(list: List<Chat>)

    fun getChatsFromLocal(): Flow<List<Chat>>

    suspend fun getChats(userID: Int): Conversation

    fun getChats(query: String): Flow<List<Chat>>
}