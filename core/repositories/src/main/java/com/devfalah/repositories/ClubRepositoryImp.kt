package com.devfalah.repositories

import com.devfalah.entities.*
import com.devfalah.repositories.mappers.toEntity
import com.devfalah.repositories.mappers.toUserInfo
import com.devfalah.usecases.repository.ClubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject

class ClubRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: ClubLocalDataSource,
) : ClubRepository {

    override suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return remoteDataSource.removeFriendRequest(
            userID = userID,
            friendRequestID = friendRequestID
        )
    }

    override suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return remoteDataSource.addFriendRequest(userID = userID, friendRequestID = friendRequestID)
    }

    override suspend fun getUserFriendRequests(userID: Int): List<User> {
        return remoteDataSource.getUserFriendRequests(userID = userID).toEntity()
    }

    override suspend fun getUserFriends(userID: Int, page: Int): Friends {
        return remoteDataSource.getUserFriends(userID, page).toEntity()
    }

    override suspend fun getNotifications(userID: Int): List<Notification> {
        return remoteDataSource.getNotifications(userID).toEntity()
    }

    override suspend fun getUserAccountDetails(userID: Int): User {
        return remoteDataSource.getUserAccountDetails(userID).toEntity()
    }

    override suspend fun getUserAlbums(userID: Int, albumID: Int): List<Album> {
        return remoteDataSource.getUserAlbums(userID, albumID).toEntity()
    }

    override suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<Post> {
        return remoteDataSource.getProfilePosts(userID, profileUserID).toEntity()
    }

    override suspend fun setLikeOnPost(userID: Int, postId: Int): Int {
        return remoteDataSource.setLikeOnPost(userID = userID, postId = postId).toEntity()
    }

    override suspend fun removeLikeOnPost(userID: Int, postId: Int): Int {
        return remoteDataSource.removeLikeOnPost(userID = userID, postId = postId).toEntity()
    }

    override suspend fun checkFriendShip(userID: Int, friendID: Int): FriendShip {
        return remoteDataSource.getFriendShipStatus(userID, friendID).toEntity()
    }

    override suspend fun addProfilePicture(userID: Int, file: File): User {
        return remoteDataSource.addProfilePicture(userID = userID, file).toEntity()
    }

    override suspend fun getProfilePostsPager(
        userID: Int, profileUserID: Int, page: Int,
    ): List<Post> {
        return remoteDataSource.getProfilePostsPager(userID, profileUserID, page).toEntity()
    }

    override suspend fun getGroupsIDsThatUserMemberOF(userID: Int): List<Club> {
        return remoteDataSource.getGroupsThatUserMemberOF(userID).toEntity()
    }


    override suspend fun getUserHomePosts(userID: Int, page: Int): List<Post> {
        return remoteDataSource.getUserHomePosts(userID, page = page).toEntity()
    }

    override suspend fun isPostSavedLocally(postId: Int): Boolean {
        return localDataSource.isPostFound(postId)
    }

    override suspend fun getSavedPostedIds(): Flow<List<Int>> {
        return localDataSource.getPostsIds()
    }

    override suspend fun getSavedPosted(): Flow<List<Post>> {
        return localDataSource.getPosts().map { it.toEntity() }
    }

    override suspend fun savedPosted(post: Post) {
        localDataSource.insertPost(post.toEntity())
    }

    override suspend fun deletePost(userId: Int, postId: Int): Boolean {
        return remoteDataSource.deletePostById(userId, postId)
    }

    override suspend fun getSearch(userID: Int, keyword: String): SearchResult {
        return remoteDataSource.getSearchResult(userID, keyword).toEntity()
    }

    override suspend fun createClub(
        userID: Int,
        groupName: String,
        description: String,
        groupPrivacy: Int,
    ): Club {
        return remoteDataSource.createClub(
            userID = userID,
            groupName = groupName,
            description = description,
            groupPrivacy = groupPrivacy
        ).toEntity()
    }

    override suspend fun editUserInformation(user: UserInformation): User {
        return remoteDataSource.editUserInformation(user.toUserInfo()).toEntity()
    }

    override suspend fun getRequestsToClub(clubId: Int): List<User> {
        return remoteDataSource.getRequestsToClub(clubId).toEntity()
    }

    override suspend fun declineClubRequest(userId: Int, memberId: Int, clubId: Int): Boolean {
        return remoteDataSource
            .declineClubRequest(userId = userId, memberId = memberId, clubId = clubId)
    }

    override suspend fun acceptClubRequest(userId: Int, memberId: Int, clubId: Int): Boolean {
        return remoteDataSource
            .acceptClubRequest(userId = userId, memberId = memberId, clubId = clubId)
    }

    override suspend fun deletePost(postId: Int) {
        localDataSource.deletePostById(postId)
    }

    override suspend fun publishPostUserWall(
        userId: Int, publishOnId: Int, postContent: String, privacy: Int
    ): Post {
        return remoteDataSource.publishPostUserWall(userId, publishOnId, postContent, privacy)
            .toEntity() ?: throw Throwable("null data")
    }

    override suspend fun publishPostWithImage(
        userId: Int, publishOnId: Int, postContent: String, privacy: Int, imageFile: File
    ): Post {
        return remoteDataSource.publishPostWithImage(userId, publishOnId, postContent, privacy, imageFile)
            .toEntity() ?: throw Throwable("null data")
    }
}