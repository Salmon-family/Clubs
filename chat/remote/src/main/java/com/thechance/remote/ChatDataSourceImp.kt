package com.thechance.remote

import com.devfalah.repository.ChatDataSource
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.MessagesDTO
import javax.inject.Inject

class ChatDataSourceImp @Inject constructor(
    private val service: ChatService
) : ChatDataSource {


    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO {
        return service.getConversationWithFriend(userID, friendID).body()?.payload
            ?: throw Throwable("Error")
    }

    override suspend fun setSendMessage(from: Int, to: Int, message: String): MessagesDTO {
        return service.sendMessage(from, to, message).body()?.payload
            ?: throw Throwable("Error")
    }
}