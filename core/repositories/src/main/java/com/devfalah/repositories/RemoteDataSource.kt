package com.devfalah.repositories

import com.devfalah.repositories.models.*
import com.devfalah.repositories.models.album.AlbumDTO
import com.devfalah.repositories.models.group.GroupDTO
import com.devfalah.repositories.models.group.GroupWallDto
import com.devfalah.repositories.models.notification.NotificationsDTO
import com.devfalah.repositories.models.post.WallPostDTO
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

    suspend fun setLikeOnComment(userID: Int, commentId: Int): ReactionDTO

    suspend fun removeLikeOnComment(userID: Int, commentId: Int): ReactionDTO

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

    suspend fun getGroupDetails(userID: Int, groupID: Int): GroupDTO

    suspend fun createClub(
        userID: Int, groupName: String, description: String, groupPrivacy: Int
    ): GroupDTO

    suspend fun editUserInformation(user: UserInfo): UserDTO

    suspend fun getGroupMembers(groupID: Int, page: Int): List<UserDTO>

    suspend fun getGroupWallList(userID: Int, groupID: Int, page: Int): GroupWallDto

    suspend fun joinClub(clubId: Int, userId: Int): GroupDTO

    suspend fun unJoinClub(clubId: Int, userId: Int): GroupDTO

    suspend fun declineClub(clubId: Int, memberId: Int, userId: Int): Boolean

    suspend fun publishPostUserWall(
        userId: Int, publishOnId: Int, postContent: String, privacy: Int
    ): WallPostDTO

    suspend fun publishPostWithImage(
        userId: Int, publishOnId: Int, postContent: String, privacy: Int, imageFile: File
    ): WallPostDTO

    suspend fun getUserGroups(userId: Int): List<GroupDTO>

    suspend fun getPostComments(postId: Int, userId: Int, page: Int): List<CommentDto>

    suspend fun getPostByID(postId: Int, userID: Int): WallPostDTO

    suspend fun addComment(userId: Int, postId: Int, comment: String): AddedCommentDTO

    suspend fun editClub(
        clubId: Int, userID: Int, clubName: String, description: String, clubPrivacy: Int,
    ): Boolean

    suspend fun deleteComment(userId: Int, commentId: Int): Boolean

    suspend fun editComment(commentId: Int, comment: String): Boolean

    suspend fun markNotificationMarkedAsViewed(notificationId: Int)

}