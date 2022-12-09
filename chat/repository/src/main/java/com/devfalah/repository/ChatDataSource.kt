package com.devfalah.repository

import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.ChatDTO
import com.devfalah.repository.models.NotificationDto

interface ChatDataSource {

    suspend fun getChats(userID: Int): ConversationDTO

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO

    suspend fun setSendMessage(from: Int, to: Int, message: String): ChatDTO

    suspend fun postNotification(notification: NotificationDto): Boolean
}