package com.devfalah.repository

import com.devfalah.repository.mappers.*
import com.nadafeteiha.usecases.ChatRepository
import com.thechance.entities.Chat
import com.thechance.entities.Conversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val dataSource: ChatDataSource,
    private val localDataSource: LocalDataSource
) : ChatRepository {

    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): Conversation {
       return dataSource.getMessagesWithFriend(userID,friendID).toEntity(userID)
    }

    override suspend fun insertChatsLocally(list: List<Chat>) {
        localDataSource.insertChats(list.map { it.toLocalModel() })
    }

    override fun getChatsFromLocal(): Flow<List<Chat>> {
        return localDataSource.getChats().map { list -> list.map { it.toEntity() } }
    }

    override suspend fun getChats(userID: Int): Conversation {
        return dataSource.getChats(userID).toEntity(userID)
    }

    override fun getChats(query: String): Flow<List<Chat>> {
        return localDataSource.getChats(query).map { list -> list.map { it.toEntity() } }
    }
}