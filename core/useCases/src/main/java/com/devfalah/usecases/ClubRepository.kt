package com.devfalah.usecases

import com.devfalah.entities.Album
import com.devfalah.entities.Notifications
import com.devfalah.entities.Post
import com.devfalah.entities.User

interface ClubRepository {

    suspend fun getUserFriends(userID: Int): List<User>

    suspend fun getNotifications(userID: Int): List<Notifications>

    suspend fun getUserAccountDetails(userID: Int): User

    suspend fun getUserAlbums(userID: Int, albumID: Int): List<Album>

    suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<Post>

    suspend fun setLike(userID: Int, postId: Int, type: String): Int

    suspend fun removeLike(userID: Int, postId: Int, type: String): Int

}