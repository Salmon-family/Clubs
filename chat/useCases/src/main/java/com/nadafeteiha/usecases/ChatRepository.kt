package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import com.thechance.entities.Message
import com.thechance.entities.Notification
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun insertChats(list: List<Chat>)

    fun getChats(): Flow<List<Chat>>

    suspend fun getChats(userID: Int): List<Chat>

    fun getChats(query: String): Flow<List<Chat>>

    suspend fun getMessages(userID: Int, friendID: Int): List<Message>

    suspend fun sendMessage(from: Int, to: Int, message: String): Message

    suspend fun insertMessages(message: List<Message>)

    suspend fun insertMessage(message: Message)

    fun getMessages(friendId: Int): Flow<List<Message>>

    fun onReceiveMessage():  Flow<Message>

    suspend fun postNotification(notification: Notification): Boolean

    suspend fun updateRecentMessage(id: Int, recentMessage: String)
}