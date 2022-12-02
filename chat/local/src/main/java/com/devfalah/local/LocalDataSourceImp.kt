package com.devfalah.local

import com.devfalah.repository.LocalDataSource
import com.devfalah.repository.models.ChatEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor (
    private val chatDao: ChatDao
) : LocalDataSource {
    override suspend fun insertChats(chats: List<ChatEntity>) {
        return chatDao.insertChats(chats)
    }

    override fun getChats(): Flow<List<ChatEntity>> {
        return chatDao.getChats()
    }
}