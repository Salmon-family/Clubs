package com.devfalah.repositories

import com.devfalah.repositories.models.*
import com.devfalah.repositories.models.album.AlbumDTO
import com.devfalah.repositories.models.group.GroupDTO
import com.devfalah.repositories.models.group.GroupWallDto
import com.devfalah.repositories.models.notification.NotificationsDTO
import java.io.File

interface RemoteDataSource {

    suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun getUserFriendRequests(userID: Int): List<FriendDTO>

    suspend fun getUserFriends(userID: Int, page: Int): FriendsDTO

    suspend fun getNotifications(userID: Int): List<NotificationsDTO>

    suspend fun getUserAccountDetails(userID: Int): UserDTO

    suspend fun getUserAlbums(userID: Int, albumID: Int): List<AlbumDTO>

    suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<WallPostDTO>

    suspend fun setLikeOnPost(userID: Int, postId: Int): ReactionDTO

    suspend fun removeLikeOnPost(userID: Int, postId: Int): ReactionDTO

    suspend fun getFriendShipStatus(userID: Int, friendID: Int): FriendshipDTO

    suspend fun addProfilePicture(userID: Int, file: File): UserDTO

    suspend fun getProfilePostsPager(userID: Int, profileUserID: Int, page: Int): List<WallPostDTO>

    suspend fun getGroupsThatUserMemberOF(userID: Int): List<GroupDTO>

    suspend fun getUserHomePosts(userID: Int, page: Int): List<WallPostDTO>

    suspend fun deletePostById(userId: Int, postId: Int): Boolean

    suspend fun getSearchResult(userId: Int, keyword: String): SearchResultDto

    suspend fun getRequestsToClub(clubId: Int): List<UserDTO>

    suspend fun declineClubRequest(userId: Int, memberId: Int, clubId: Int): Boolean

    suspend fun acceptClubRequest(userId: Int, memberId: Int, clubId: Int): Boolean

    suspend fun createClub(
        userID: Int,
        groupName: String,
        description: String,
        groupPrivacy: Int
    ): GroupDTO

    suspend fun getGroupDetails(userID: Int, groupID: Int): GroupDTO

    suspend fun getGroupMembers(groupID: Int): Int

    suspend fun getGroupWallList(userID: Int, groupID: Int): GroupWallDto
}