package com.devfalah.usecases

import com.devfalah.entities.Notifications
import com.devfalah.entities.User

interface ClubRepository {

    suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun getUserFriendRequests(userID: Int): List<User>

    suspend fun getUserFriends(userID: Int): List<User>

    suspend fun getNotifications(userID: Int): List<Notifications>


}