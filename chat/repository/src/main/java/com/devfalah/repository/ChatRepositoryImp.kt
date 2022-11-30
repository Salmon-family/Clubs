package com.devfalah.repository

import com.nadafeteiha.usecases.ChatRepository
import com.thechance.entities.Conversation
import com.devfalah.repository.mappers.toEntity
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val dataSource: ChatDataSource,
) : ChatRepository {

    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): Conversation {
       return dataSource.getMessagesWithFriend(userID,friendID).toEntity()
    }


}