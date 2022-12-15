package com.devfalah.repositories

import com.devfalah.repositories.models.FriendDTO
import com.devfalah.repositories.models.ReactionDTO
import com.devfalah.repositories.models.UserDTO
import com.devfalah.repositories.models.WallPostDTO
import com.devfalah.repositories.models.album.AlbumDTO
import com.devfalah.repositories.models.group.GroupDTO
import com.devfalah.repositories.models.notification.NotificationsDTO

interface RemoteDataSource {

    suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun getUserFriendRequests(userID: Int): List<FriendDTO>

    suspend fun getUserFriends(userID: Int): List<FriendDTO>

    suspend fun getNotifications(userID: Int): List<NotificationsDTO>

    suspend fun getUserAccountDetails(userID: Int): UserDTO

    suspend fun getUserAlbums(userID: Int, albumID: Int): List<AlbumDTO>

    suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<WallPostDTO>

    suspend fun setLike(userID: Int, postId: Int, type: String): ReactionDTO

    suspend fun removeLike(userID: Int, postId: Int, type: String): ReactionDTO

    suspend fun checkFriendShip(userID: Int, friendID: Int): Boolean

    suspend fun getUserGroups(userId: Int): List<GroupDTO>

}