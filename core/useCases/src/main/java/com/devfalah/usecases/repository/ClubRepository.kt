package com.devfalah.usecases.repository

import com.devfalah.entities.Album
import com.devfalah.entities.Notification
import com.devfalah.entities.Post
import com.devfalah.entities.User
import java.io.File

interface ClubRepository {

    suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun getUserFriendRequests(userID: Int): List<User>

    suspend fun getUserFriends(userID: Int): List<User>

    suspend fun getNotifications(userID: Int): List<Notification>

    suspend fun getUserAccountDetails(userID: Int): User

    suspend fun getUserAlbums(userID: Int, albumID: Int): List<Album>

    suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<Post>

    suspend fun setLikeOnPost(userID: Int, postId: Int): Int

    suspend fun removeLikeOnPost(userID: Int, postId: Int): Int

    suspend fun checkFriendShip(userID: Int, friendID: Int): Boolean

    suspend fun addProfilePicture(userID: Int, file: File): User

}