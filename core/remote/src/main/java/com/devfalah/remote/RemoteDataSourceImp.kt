package com.devfalah.remote

import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repositories.models.FriendDTO
import com.devfalah.repositories.models.UserDTO
import com.devfalah.repositories.models.notification.NotificationsDTO
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ClubService,
) : RemoteDataSource {

    override suspend fun getUserFriends(userID: Int): List<FriendDTO> {
        return apiService.getUserFriends(userID).body()?.payload?.list ?: throw Throwable("Error")
    }

    override suspend fun getNotifications(userID: Int): List<NotificationsDTO> {
        return apiService.getNotifications(userID).body()?.payload?.list ?: throw Throwable("Error")
    }

}