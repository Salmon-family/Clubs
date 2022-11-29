package com.devfalah.usecases

import com.devfalah.entities.User

interface ClubRepository {

    suspend fun getUserFriends(userID: Int): List<User>

}