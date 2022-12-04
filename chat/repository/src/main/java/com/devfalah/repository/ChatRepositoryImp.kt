package com.devfalah.repository

import com.devfalah.repository.mappers.toEntity
import com.devfalah.repository.mappers.toLocalDto
import com.devfalah.repository.mappers.toMessage
import com.nadafeteiha.usecases.ChatRepository
import com.thechance.entities.Chat
import com.thechance.entities.Conversation
import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val dataSource: ChatDataSource,
    private val localDataSource: LocalDataSource,
) : ChatRepository {

    override suspend fun insertChatsLocally(list: List<Chat>) {
        localDataSource.insertChats(list.map { it.toLocalDto() })
    }

    override fun getChatsFromLocal(): Flow<List<Chat>> {
        return localDataSource.getChats().map { list -> list.map { it.toEntity() } }
    }

    override suspend fun getChats(userID: Int): List<Chat> {
        return dataSource.getChats(userID).map { it.toLocalDto(userID) }
    }

    override fun getChats(query: String): Flow<List<Chat>> {
        return localDataSource.getChats(query).map { list -> list.map { it.toEntity() } }
    }

    override suspend fun getMessages(userID: Int, friendID: Int): Conversation {
        return dataSource.getMessagesWithFriend(userID, friendID).toEntity(userID)
    }

    override suspend fun setSendMessage(userID: Int, friendId: Int, message: String): Message {
        return dataSource.setSendMessage(userID, friendId, message).toEntity(userID)
    }

    override suspend fun insertMessages(message: List<Message>) {
        localDataSource.insertMessages(message.map { it.toMessage() })
    }

    override fun getMessages(friendId: Int): Flow<List<Message>> {
        return localDataSource.getMessages(friendId).map { list -> list.map { it.toMessage() } }
    }

}