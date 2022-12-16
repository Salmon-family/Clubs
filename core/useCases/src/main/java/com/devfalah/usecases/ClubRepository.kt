package com.devfalah.usecases

import com.devfalah.entities.*

interface ClubRepository {

    suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun getUserFriendRequests(userID: Int): List<User>

    suspend fun getUserFriends(userID: Int): List<User>

    suspend fun getNotifications(userID: Int): List<Notifications>

    suspend fun getUserAccountDetails(userID: Int): User

    suspend fun getUserAlbums(userID: Int, albumID: Int): List<Album>

    suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<Post>

    suspend fun setLike(userID: Int, postId: Int, type: String): Int

    suspend fun removeLike(userID: Int, postId: Int, type: String): Int

    suspend fun checkFriendShip(userID: Int, friendID: Int): Boolean

    suspend fun GetUserGroups(userId: Int): List<Club>
}