package com.devfalah.remote


import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repositories.models.FriendDTO
import com.devfalah.repositories.models.ReactionDTO
import com.devfalah.repositories.models.UserDTO
import com.devfalah.repositories.models.WallPostDTO
import com.devfalah.repositories.models.album.AlbumDTO
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

    override suspend fun setLikeOnPost(userID: Int, postId: Int): ReactionDTO {
        return apiService.addLike(userID = userID, postID = postId, type = LikeType.post.name)
            .body()?.payload ?: throw Throwable("Error")
    }

    override suspend fun removeLikeOnPost(userID: Int, postId: Int): ReactionDTO {
        return apiService.removeLike(userID = userID, postID = postId, type = LikeType.post.name)
            .body()?.payload ?: throw Throwable("Error")
    }

    override suspend fun checkFriendShip(userID: Int, friendID: Int): Boolean {
        return apiService.isFriendWith(userID = userID, otherUserID = friendID)
            .body()?.payload?.isFriend ?: throw Throwable("error")
    }

}