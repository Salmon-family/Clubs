package com.devfalah.repositories

import com.devfalah.repositories.models.FriendDTO
import com.devfalah.repositories.models.notification.NotificationsDTO

interface RemoteDataSource {

    suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun getUserFriendRequests(userID: Int): List<FriendDTO>

    suspend fun getUserFriends(userID: Int): List<FriendDTO>

    suspend fun getNotifications(userID: Int): List<NotificationsDTO>

}