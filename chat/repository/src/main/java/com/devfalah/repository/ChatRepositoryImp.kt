package com.devfalah.repository

import com.devfalah.repository.mappers.*
import com.nadafeteiha.usecases.ChatRepository
import com.thechance.entities.Chat
import com.thechance.entities.Message
import com.thechance.entities.Messages
import com.thechance.entities.Notification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val chatRemoteDataSource: ChatRemoteDataSource,
    private val chatLocalDataSource: ChatLocalDataSource,
    private val firebaseDataSource: ChatFirebaseDataSource,
) : ChatRepository {

    override suspend fun insertChats(list: List<Chat>) {
        chatLocalDataSource.insertChats(list.map { it.toLocalDto() })
    }

    override fun getChats(): Flow<List<Chat>> {
        return chatLocalDataSource.getChats().map { list -> list.map { it.toEntity() } }
    }

    override suspend fun getChats(userID: Int): List<Chat> {
        return chatRemoteDataSource.getChats(userID).map { it.toLocalDto(userId = userID) }
    }

    override fun getChats(query: String): Flow<List<Chat>> {
        return chatLocalDataSource.getChats(query).map { list -> list.map { it.toEntity() } }
    }

    override suspend fun getMessages(userID: Int, friendID: Int, page: Int): Messages {
        return chatRemoteDataSource.getMessagesWithFriend(userID, friendID, page).toEntity(userID)
    }

    override suspend fun sendMessage(from: Int, to: Int, message: String): Message {
        return chatRemoteDataSource.sendMessage(from, to, message).toEntity(from)
    }

    override suspend fun insertMessages(message: List<Message>) {
        chatLocalDataSource.insertMessages(message.map { it.toMessage() })
    }

    override suspend fun insertMessage(message: Message) {
        chatLocalDataSource.insertMessage(message.toMessage())
    }

    override fun onReceiveMessage(): Flow<Message> {
        return firebaseDataSource.onReceiveNotification().map { it.toMessage() }
    }

    override suspend fun postNotification(notification: Notification): Boolean {
        return chatRemoteDataSource.postNotification(notification.toDto())
    }

    override suspend fun updateRecentMessage(id: Int, recentMessage: String) {
        chatLocalDataSource.updateRecentMessage(id, recentMessage)
    }

    override suspend fun getMessages(friendId: Int): Flow<List<Message>> {
        return chatLocalDataSource.getMessages(friendId).map { list -> list.map { it.toMessage() } }
    }

}