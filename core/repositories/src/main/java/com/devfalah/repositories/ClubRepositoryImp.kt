package com.devfalah.repositories

import com.devfalah.entities.*
import com.devfalah.repositories.mappers.toEntities
import com.devfalah.repositories.mappers.toEntity
import com.devfalah.usecases.repository.ClubRepository
import java.io.File
import javax.inject.Inject

class ClubRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
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

    override suspend fun getUserFriends(userID: Int): List<User> {
        return remoteDataSource.getUserFriends(userID).toEntity()
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

    override suspend fun checkFriendShip(userID: Int, friendID: Int): Boolean {
        return remoteDataSource.checkFriendShip(userID, friendID)
    }

    override suspend fun addProfilePicture(userID: Int, file: File): User {
        return remoteDataSource.addProfilePicture(userID = userID, file).toEntity()
    }

    override suspend fun getProfilePostsPager(
        userID: Int, profileUserID: Int, page: Int
    ): List<Post> {
        return remoteDataSource.getProfilePostsPager(userID, profileUserID, page).toEntity()
    }

    override suspend fun getPostDetails(userID: Int, postID: Int): Post {
            return remoteDataSource.getPostDetails(userID, postID).toEntities()
    }

    override suspend fun getAllComments(userID: Int, postID: Int): List<Comment> {
        return remoteDataSource.getAllComments(userID, postID).toEntity()
    }

    override suspend fun addComment(userID: Int, postID: Int, content: String): Comment {
        return remoteDataSource.addComment(userID, postID, content).toEntity()
    }

    override suspend fun deleteComment(userID: Int, commentID: Int): Boolean {
        return remoteDataSource.deleteComment(userID, commentID)
    }

    override suspend fun editComment(commentID: Int, content: String): Success {
        return remoteDataSource.editComment(commentID, content).toEntity()
    }

}