package com.thechance.remote

import com.devfalah.repository.ChatDataSource
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.ChatDTO
import javax.inject.Inject

class ChatDataSourceImp @Inject constructor(
    private val service: ChatService
) : ChatDataSource {

    override suspend fun getChats(userID: Int): List<ChatDTO> {
        return service.getRecentMessages(userID = userID).body()?.payload?.list
            ?: throw Throwable("Error")
    }

    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO {
        return service.getConversationWithFriend(userID, friendID).body()?.payload
            ?: throw Throwable("Error")
    }

    override suspend fun setSendMessage(from: Int, to: Int, message: String): ChatDTO {
        return service.sendMessage(from, to, message).body()?.payload
            ?: throw Throwable("Error")
    }
}