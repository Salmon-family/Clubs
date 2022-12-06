package com.devfalah.local

import com.devfalah.repository.ChatLocalDataSource
import com.devfalah.repository.models.MessageEntityLocalDTO
import com.devfalah.repository.models.ChatLocalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val chatDao: ChatDao
) : ChatLocalDataSource {

    override suspend fun insertChats(chats: List<ChatLocalDto>) {
        return chatDao.insertChats(chats)
    }

    override fun getChats(): Flow<List<ChatLocalDto>> {
        return chatDao.getChats()
    }

    override fun getChats(query: String): Flow<List<ChatLocalDto>> {
        return chatDao.getChats(query)
    }

    override suspend fun insertMessages(messages: List<MessageEntityLocalDTO>) {
        return chatDao.insertMessages(messages)
    }

    override suspend fun insertMessage(message: MessageEntityLocalDTO) {
        chatDao.insertMessage(message)
    }

    override fun getMessages(friendId: Int): Flow<List<MessageEntityLocalDTO>> {
        return chatDao.getMessages(friendId)
    }

    override suspend fun getFriendDetails(friendID: Int): ChatLocalDto {
        return chatDao.getFriendDetails(friendID)
    }
}