package com.nadafeteiha.usecases

import com.thechance.entities.Conversation
import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): Conversation

    suspend fun setSendMessage(from: Int, to: Int, message: String): Message

    suspend fun insertMessagesLocally(message: List<Message>)

    fun getMessagesFromLocal(): Flow<List<Message>>

}