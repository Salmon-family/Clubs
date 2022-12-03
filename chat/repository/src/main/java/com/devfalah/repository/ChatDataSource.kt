package com.devfalah.repository

import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.MessagesDTO

interface ChatDataSource {

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO

    suspend fun setSendMessage(from: Int, to: Int, message: String): MessagesDTO
}