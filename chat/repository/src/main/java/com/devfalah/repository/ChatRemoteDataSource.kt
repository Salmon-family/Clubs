package com.devfalah.repository

import com.devfalah.repository.models.*

interface ChatRemoteDataSource {

    suspend fun getChats(userID: Int,page: Int): ConversationDTO

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int, page: Int): ConversationDTO

    suspend fun sendMessage(from: Int, to: Int, message: String): ChatDTO

    suspend fun postNotification(notification: NotificationDto): Boolean

    suspend fun getUserDetails(userID: Int): UserDTO

    suspend fun getAllFriends(userID: Int): List<FriendDTO>
}