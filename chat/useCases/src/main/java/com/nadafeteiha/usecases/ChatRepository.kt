package com.nadafeteiha.usecases

import com.thechance.entities.*
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ChatRepository {

    suspend fun insertChats(list: List<Chat>)

    suspend fun getChats(): Flow<List<Chat>>

    suspend fun getChats(userID: Int,page: Int): Chats

    fun getChats(query: String): Flow<List<Chat>>

    suspend fun getMessages(userID: Int, friendID: Int, page: Int): Messages

    suspend fun sendMessage(from: Int, to: Int, message: String): Message

    suspend fun insertMessages(message: List<Message>)

    suspend fun insertMessage(message: Message)

    suspend fun getMessages(friendId: Int): Flow<List<Message>>

    fun onReceiveMessage():  Flow<Int>

    suspend fun postNotification(notification: Notification): Boolean

    suspend fun updateRecentMessage(id: Int, recentMessage: String)

    suspend fun getUserDetail(userID: Int): User

    suspend fun saveToken()
}