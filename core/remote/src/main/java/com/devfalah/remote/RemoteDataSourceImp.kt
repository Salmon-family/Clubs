package com.devfalah.remote


import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repositories.models.FriendDTO
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ClubService,
) : RemoteDataSource {

    override suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return apiService.removeFriend(userID, friendRequestID).body()?.payload?.success ?: throw Throwable("Error")
    }

    override suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return apiService.addFriendRequest(userID, friendRequestID).body()?.payload?.success ?: throw Throwable("Error")
    }

    override suspend fun getUserFriendRequests(userID: Int): List<FriendDTO> {
        return apiService.getUserFriendRequests(userID).body()?.payload?.list ?: emptyList()
    }

    override suspend fun getUserFriends(userID: Int): List<FriendDTO> {
        return apiService.getUserFriends(userID).body()?.payload?.list ?: throw Throwable("Erroe")
    }

}