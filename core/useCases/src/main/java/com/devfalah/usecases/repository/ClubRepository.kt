package com.devfalah.usecases.repository

import com.devfalah.entities.*
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ClubRepository {

    suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean

    suspend fun getUserFriendRequests(userID: Int): List<User>

    suspend fun getUserFriends(userID: Int): Friends

    suspend fun getNotifications(userID: Int): List<Notification>

    suspend fun getUserAccountDetails(userID: Int): User

    suspend fun getUserAlbums(userID: Int, albumID: Int): List<Album>

    suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<Post>

    suspend fun setLikeOnPost(userID: Int, postId: Int): Int

    suspend fun removeLikeOnPost(userID: Int, postId: Int): Int

    suspend fun checkFriendShip(userID: Int, friendID: Int): FriendShip

    suspend fun addProfilePicture(userID: Int, file: File): User

    suspend fun getProfilePostsPager(userID: Int, profileUserID: Int, page: Int): List<Post>

    suspend fun getGroupsIDsThatUserMemberOF(userID: Int): List<Int>

    suspend fun getUserHomePosts(userID: Int, page: Int): List<Post>

    suspend fun isPostSavedLocally(postId: Int): Boolean

    suspend fun getSavedPostedIds(): Flow<List<Int>>

    suspend fun getSavedPosted(): Flow<List<Post>>

    suspend fun savedPosted(post: Post)

    suspend fun deletePost(postId: Int)

    suspend fun deletePost(userId: Int, postId: Int): Boolean


    suspend fun getPostDetails(userID: Int, postID: Int): Post

    suspend fun getAllComments(userID: Int, postID: Int): List<Comment>

    suspend fun addComment(userID: Int, postID: Int, content: String/*, imageFile: File?*/): Comment

    suspend fun deleteComment(userID: Int, commentID: Int): Boolean

    suspend fun editComment(commentID: Int, content: String): Success
}