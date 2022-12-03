package com.devfalah.repository

import com.devfalah.repository.entities.MessageEntity
import com.devfalah.repository.mappers.toEntity
import com.devfalah.repository.mappers.toMessage
import com.devfalah.repository.mappers.toMessagesTable
import com.nadafeteiha.usecases.ChatRepository
import com.thechance.entities.Conversation
import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val dataSource: ChatDataSource,
    private val localDataSource: LocalDataSource,
) : ChatRepository {

    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): Conversation {
        return dataSource.getMessagesWithFriend(userID, friendID).toEntity()
    }

    override suspend fun setSendMessage(from: Int, to: Int, message: String): Message {
        return dataSource.setSendMessage(from, to, message).toEntity()
    }

    override suspend fun insertMessagesLocally(message: List<Message>) {
        localDataSource.insertMessages(message.map { it.toMessage() })
    }

    override fun getMessagesFromLocal(): Flow<List<Message>> {
        return localDataSource.getMessages().map { list -> list.map { it.toMessage() } }
    }

}