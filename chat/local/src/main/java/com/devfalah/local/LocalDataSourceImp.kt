package com.devfalah.local

import com.devfalah.repository.LocalDataSource
import com.devfalah.repository.models.ChatTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor (
    private val chatDao: ChatDao
) : LocalDataSource {
    override suspend fun insertChats(chats: List<ChatTable>) {
        return chatDao.insertChats(chats)
    }

    override fun getChats(): Flow<List<ChatTable>> {
        return chatDao.getChats()
    }
}