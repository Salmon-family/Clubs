package com.devfalah.usecases.repository

import com.devfalah.entities.*
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ClubRepository {

    suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun getUserFriendRequests(userID: Int): List<User>

    suspend fun getUserFriends(userID: Int, page: Int): Friends

    suspend fun getNotifications(userID: Int): List<Notification>

    suspend fun getUserAccountDetails(userID: Int): User

    suspend fun getUserAlbums(userID: Int, albumID: Int): List<Album>

    suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<Post>

    suspend fun setLikeOnPost(userID: Int, postId: Int): Int

    suspend fun removeLikeOnPost(userID: Int, postId: Int): Int

    suspend fun checkFriendShip(userID: Int, friendID: Int): FriendShip

    suspend fun addProfilePicture(userID: Int, file: File): User

    suspend fun getProfilePostsPager(userID: Int, profileUserID: Int, page: Int): List<Post>

    suspend fun getGroupsIDsThatUserMemberOF(userID: Int): List<Club>

    suspend fun getUserHomePosts(userID: Int, page: Int): List<Post>

    suspend fun isPostSavedLocally(postId: Int): Boolean

    suspend fun getSavedPostedIds(groupId: Int): Flow<List<Int>>

    suspend fun getSavedPosted(): Flow<List<Post>>

    suspend fun savedPosted(post: Post)

    suspend fun deletePost(postId: Int)

    suspend fun deletePost(userId: Int, postId: Int): Boolean

    suspend fun getSearch(userID: Int, keyword: String): SearchResult

    suspend fun getRequestsToClub(clubId: Int): List<User>

    suspend fun declineClubRequest(userId: Int, memberId: Int, clubId: Int): Boolean

    suspend fun acceptClubRequest(userId: Int, memberId: Int, clubId: Int): Boolean

    suspend fun getGroupDetails(userID: Int, groupID: Int): Club

    suspend fun getGroupMembers(groupID: Int): List<User>

    suspend fun getGroupWallList(userID: Int, groupID: Int, page: Int): GroupWall

    suspend fun joinClub(clubId: Int, userId: Int): Club

    suspend fun unJoinClub(clubId: Int, userId: Int): Club

    suspend fun declineClub(clubId: Int, memberId: Int, userId: Int): Boolean

    suspend fun createClub(userID: Int, groupName: String, description: String, groupPrivacy: Int)
            : Club

    suspend fun editUserInformation(user: UserInformation): User

    suspend fun publishPostUserWall(
        userId: Int, publishOnId: Int, postContent: String, privacy: Int
    ): Post

    suspend fun publishPostWithImage(
        userId: Int, publishOnId: Int, postContent: String, privacy: Int, imageFile: File
    ): Post

    suspend fun editClub(
        clubId: Int,
        userID: Int,
        clubName: String,
        description: String,
        clubPrivacy: Int,
    ): Boolean
}