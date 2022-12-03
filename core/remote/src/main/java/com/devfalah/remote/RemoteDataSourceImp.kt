package com.devfalah.remote

import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repositories.models.FriendDTO
import com.devfalah.repositories.models.UserDTO
import com.devfalah.repositories.models.WallPostDTO
import com.devfalah.repositories.models.album.AlbumDTO
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

    override suspend fun getUserAccountDetails(userID: Int): UserDTO {
        return apiService.getUserDetails(userID).body()?.payload ?: throw Throwable("Error")
    }

    override suspend fun getUserAlbums(userID: Int, albumID: Int): List<AlbumDTO> {
        return apiService.getAlbums(userID, userID).body()?.payload?.albums
            ?: throw Throwable("Error")
    }

    override suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<WallPostDTO> {
        return apiService.getAllWallPosts(userID, profileUserID).body()?.payload?.posts
            ?: throw Throwable(
                "Error"
            )
    }

}