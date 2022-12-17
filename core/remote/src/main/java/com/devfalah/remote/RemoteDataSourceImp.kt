package com.devfalah.remote


import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repositories.models.*
import com.devfalah.repositories.models.album.AlbumDTO
import com.devfalah.repositories.models.group.GroupDTO
import com.devfalah.repositories.models.notification.NotificationsDTO
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ClubService,
) : RemoteDataSource {

    override suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return apiService.removeFriend(userID, friendRequestID).body()?.payload?.success
            ?: throw Throwable("Error")
    }

    override suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return apiService.addFriendRequest(userID, friendRequestID).body()?.payload?.success
            ?: throw Throwable("Error")
    }

    override suspend fun getUserFriendRequests(userID: Int): List<FriendDTO> {
        return apiService.getUserFriendRequests(userID).body()?.payload?.list ?: emptyList()
    }

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
            ?: throw Throwable("Error")
    }

    override suspend fun setLike(userID: Int, postId: Int, type: String): ReactionDTO {
        return apiService.addLike(userID = userID, postID = postId, type = type).body()?.payload
            ?: throw Throwable("Error")
    }

    override suspend fun removeLike(userID: Int, postId: Int, type: String): ReactionDTO {
        return apiService.removeLike(userID = userID, postID = postId, type = type).body()?.payload
            ?: throw Throwable("Error")
    }

    override suspend fun checkFriendShip(userID: Int, friendID: Int): Boolean {
        return apiService.isFriendWith(userID = userID, otherUserID = friendID)
            .body()?.payload?.isFriend
            ?: throw Throwable("error")
    }

    override suspend fun getUserGroups(userId: Int): List<GroupDTO> {
        return apiService.getAllUserGroups(userId).body()?.payload?.groups
            ?: throw Throwable("empty groups")
    }

    override suspend fun search(userId: Int, keyword: String): SearchDTO {
        return apiService.search(userId, keyword).body()?.payload
            ?: throw Throwable("empty search")
    }

}