package com.thechance.remote

import com.thechance.repositories.ChatDataSource
import com.thechance.repositories.models.ConversationDTO
import javax.inject.Inject

class ChatDataSourceImp @Inject constructor(
    private val service: ChatService
) : ChatDataSource {


    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO {
        return service.getConversationWithFriend(userID, friendID).body()?.payload
            ?: throw Throwable("Error")
    }


}