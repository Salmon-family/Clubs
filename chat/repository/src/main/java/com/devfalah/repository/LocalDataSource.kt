package com.devfalah.repository

import com.devfalah.repository.entities.MessageEntityLocalDTO
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertMessages(messages: List<MessageEntityLocalDTO>)

    fun getMessages(friendId: Int): Flow<List<MessageEntityLocalDTO>>

}