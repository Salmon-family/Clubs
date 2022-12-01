package com.devfalah.usecases

import com.devfalah.entities.Notifications
import com.devfalah.entities.User

interface ClubRepository {

    suspend fun getUserFriends(userID: Int): List<User>

    suspend fun getNotifications(userID: Int): List<Notifications>


}