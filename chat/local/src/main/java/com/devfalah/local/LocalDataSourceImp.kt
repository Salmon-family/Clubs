package com.devfalah.local

import com.devfalah.repository.LocalDataSource
import com.devfalah.repository.models.ChatLocalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val chatDao: ChatDao,
) : LocalDataSource {
    override suspend fun insertChats(chats: List<ChatLocalDto>) {
        return chatDao.insertChats(chats)
    }

    override fun getChats(): Flow<List<ChatLocalDto>> {
        return chatDao.getChats()
    }

    override fun getChats(query: String): Flow<List<ChatLocalDto>> {
        return chatDao.getChats(query)
    }
}