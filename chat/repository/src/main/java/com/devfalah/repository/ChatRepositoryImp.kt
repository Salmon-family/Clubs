package com.devfalah.repository

import com.devfalah.repository.mappers.toEntity
import com.devfalah.repository.mappers.toFriend
import com.devfalah.repository.mappers.toLocalDto
import com.devfalah.repository.mappers.toMessage
import com.nadafeteiha.usecases.ChatRepository
import com.thechance.entities.Chat
import com.thechance.entities.Conversation
import com.thechance.entities.Friend
import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val ChatDataSource: ChatDataSource,
    private val chatLocalDataSource: ChatLocalDataSource,
) : ChatRepository {

    override suspend fun insertChatsLocally(list: List<Chat>) {
        chatLocalDataSource.insertChats(list.map { it.toLocalDto() })
    }

    override fun getChatsFromLocal(): Flow<List<Chat>> {
        return chatLocalDataSource.getChats().map { list -> list.map { it.toEntity() } }
    }

    override suspend fun getChats(userID: Int): List<Chat> {
        return ChatDataSource.getChats(userID).map { it.toLocalDto(userID) }
    }

    override fun getChats(query: String): Flow<List<Chat>> {
        return chatLocalDataSource.getChats(query).map { list -> list.map { it.toEntity() } }
    }

    override suspend fun getMessages(userID: Int, friendID: Int): Conversation {
        return ChatDataSource.getMessagesWithFriend(userID, friendID).toEntity(userID)
    }

    override suspend fun setSendMessage(userID: Int, friendId: Int, message: String): Message {
        return ChatDataSource.setSendMessage(userID, friendId, message).toEntity(userID)
    }

    override suspend fun insertMessages(message: List<Message>) {
        chatLocalDataSource.insertMessages(message.map { it.toMessage() })
    }

    override suspend fun insertMessage(message: Message) {
        chatLocalDataSource.insertMessage(message.toMessage())
    }

    override suspend fun getFriendDetails(friendID: Int): Friend {
        return chatLocalDataSource.getFriendDetails(friendID).toFriend()
    }

    override fun getMessages(friendId: Int): Flow<List<Message>> {
        return chatLocalDataSource.getMessages(friendId).map { list -> list.map { it.toMessage() } }
    }

}