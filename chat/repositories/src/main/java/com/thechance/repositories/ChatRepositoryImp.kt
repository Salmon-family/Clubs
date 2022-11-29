package com.thechance.repositories

import com.nadafeteiha.usecases.ChatRepository
import com.thechance.entities.Conversation
import com.thechance.repositories.mappers.toEntity
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val dataSource: ChatDataSource,
) : ChatRepository {

    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): Conversation {
       return dataSource.getMessagesWithFriend(userID,friendID).toEntity()
    }


}