package com.thechance.remote

import com.devfalah.repository.ChatDataSource
import com.devfalah.repository.models.ConversationDTO
import javax.inject.Inject

class ChatDataSourceImp @Inject constructor(
    private val service: ChatService
) : ChatDataSource {


    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO {
        return service.getConversationWithFriend(userID, friendID).body()?.payload
            ?: throw Throwable("Error")
    }


}