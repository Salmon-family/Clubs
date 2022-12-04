package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import com.thechance.entities.Conversation
import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun insertChatsLocally(list: List<Chat>)

    fun getChatsFromLocal(): Flow<List<Chat>>

    suspend fun getChats(userID: Int): List<Chat>

    fun getChats(query: String): Flow<List<Chat>>

    suspend fun getMessages(userID: Int, friendID: Int): Conversation

    suspend fun setSendMessage(from: Int, to: Int, message: String): Message

    suspend fun insertMessages(message: List<Message>)

    fun getMessages(friendId: Int): Flow<List<Message>>

}