package com.devfalah.repositories

import com.devfalah.repositories.models.FriendDTO
import com.devfalah.repositories.models.PostDTO
import com.devfalah.repositories.models.UserDTO
import com.devfalah.repositories.models.WallPostDTO
import com.devfalah.repositories.models.album.AlbumDTO
import com.devfalah.repositories.models.notification.NotificationsDTO

interface RemoteDataSource {

    suspend fun getUserFriends(userID: Int): List<FriendDTO>

    suspend fun getNotifications(userID: Int): List<NotificationsDTO>

    suspend fun getUserAccountDetails(userID: Int): UserDTO

    suspend fun getUserAlbums(userID: Int, albumID: Int): List<AlbumDTO>

    suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<WallPostDTO>

}