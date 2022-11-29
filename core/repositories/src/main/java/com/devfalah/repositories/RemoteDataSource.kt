package com.devfalah.repositories

import com.devfalah.repositories.models.FriendDTO

interface RemoteDataSource {

    suspend fun getUserFriends(userID: Int): List<FriendDTO>

}