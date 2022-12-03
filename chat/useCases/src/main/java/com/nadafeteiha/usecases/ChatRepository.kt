package com.nadafeteiha.usecases

import com.thechance.entities.Conversation
import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getMessages(userID: Int, friendID: Int): Conversation

    suspend fun setSendMessage(from: Int, to: Int, message: String): Message

    suspend fun insertMessages(message: List<Message>)

    fun getMessages(friendId: Int): Flow<List<Message>>

}