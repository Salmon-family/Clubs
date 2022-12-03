package com.devfalah.local

import com.devfalah.repository.LocalDataSource
import com.devfalah.repository.entities.MessageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val messagesDto: ChatDao
) : LocalDataSource {

    override suspend fun insertMessages(messages: List<MessageEntity>) {
        return messagesDto.insertMessages(messages)
    }

    override fun getMessages(): Flow<List<MessageEntity>> {
        return messagesDto.getMessages()
    }
}