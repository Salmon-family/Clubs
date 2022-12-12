package com.devfalah.repository

import com.devfalah.repository.models.ChatDTO
import com.devfalah.repository.models.NotificationDto

interface ChatRemoteDataSource {

    suspend fun getChats(userID: Int, page: Int): List<ChatDTO>

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): List<ChatDTO>

    suspend fun sendMessage(from: Int, to: Int, message: String): ChatDTO

    suspend fun postNotification(notification: NotificationDto): Boolean

    suspend fun getChatsCount(userID: Int): Int
}