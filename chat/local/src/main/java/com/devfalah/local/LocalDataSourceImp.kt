package com.devfalah.local

import com.devfalah.repository.LocalDataSource
import com.devfalah.repository.entities.MessageEntityLocalDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val messagesDto: ChatDao
) : LocalDataSource {

    override suspend fun insertMessages(messages: List<MessageEntityLocalDTO>) {
        return messagesDto.insertMessages(messages)
    }

    override fun getMessages(friendId: Int): Flow<List<MessageEntityLocalDTO>> {
        return messagesDto.getMessages(friendId)
    }
}