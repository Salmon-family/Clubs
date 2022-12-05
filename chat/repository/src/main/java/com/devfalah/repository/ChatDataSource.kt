package com.devfalah.repository

import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.ChatDTO

interface ChatDataSource {

    suspend fun getChats(userID: Int): List<ChatDTO>

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO

    suspend fun setSendMessage(from: Int, to: Int, message: String): ChatDTO
}