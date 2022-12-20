package com.devfalah.remote


import com.devfalah.remote.response.BaseResponse
import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repositories.models.*
import com.devfalah.repositories.models.album.AlbumDTO
import com.devfalah.repositories.models.group.GroupDTO
import com.devfalah.repositories.models.notification.NotificationsDTO
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService: ClubService,
) : RemoteDataSource {

    override suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return wrap { apiService.removeFriend(userID, friendRequestID) }.success ?: throw Throwable(
            "Mapping Error"
        )
    }

    override suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return wrap { apiService.addFriendRequest(userID, friendRequestID) }.success
            ?: throw Throwable("Mapping Error")
    }

    override suspend fun getUserFriendRequests(userID: Int): List<FriendDTO> {
        return wrap { apiService.getUserFriendRequests(userID) }.list
            ?: throw Throwable("Mapping Error")
    }

    override suspend fun getUserFriends(userID: Int, page: Int): FriendsDTO {
        return wrap { apiService.getUserFriends(userID, page) }
    }

    override suspend fun getNotifications(userID: Int): List<NotificationsDTO> {
        return wrap { apiService.getNotifications(userID) }.list ?: throw Throwable("Mapping Error")
    }

    override suspend fun getUserAccountDetails(userID: Int): UserDTO {
        return wrap { apiService.getUserDetails(userID) }
    }

    override suspend fun getUserAlbums(userID: Int, albumID: Int): List<AlbumDTO> {
        return apiService.getAlbums(userID, userID).body()?.payload?.albums
            ?: throw Throwable("Error")
    }

    override suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<WallPostDTO> {
        return apiService.getAllWallPosts(userID, profileUserID).body()?.payload?.posts
            ?: throw Throwable("Error")
    }

    override suspend fun getProfilePostsPager(
        userID: Int,
        profileUserID: Int,
        page: Int
    ): List<WallPostDTO> {
        return wrap { apiService.getAllWallPosts(userID, profileUserID, page = page) }.posts
            ?: throw Throwable("Mapping Error")
    }

    override suspend fun setLikeOnPost(userID: Int, postId: Int): ReactionDTO {
        return wrap {
            apiService.addLike(userID = userID, postID = postId, type = LikeType.post.name)
        }
    }

    override suspend fun removeLikeOnPost(userID: Int, postId: Int): ReactionDTO {
        return wrap {
            apiService.removeLike(userID = userID, postID = postId, type = LikeType.post.name)
        }
    }

    override suspend fun getFriendShipStatus(userID: Int, friendID: Int): FriendshipDTO {
        return wrap { apiService.isFriendWith(userID = userID, otherUserID = friendID) }
    }

    override suspend fun addProfilePicture(userID: Int, file: File): UserDTO {
        val requestBody = file.asRequestBody("image/${file.extension}".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("userphoto", file.name, requestBody)
        val id = RequestBody.create("text/plain".toMediaTypeOrNull(), userID.toString())
        return apiService.addProfilePicture(userId = id, file = part).body()?.payload
            ?: throw Throwable("Error")
    }

    override suspend fun getGroupsThatUserMemberOF(userID: Int): List<GroupDTO> {
        return wrap { apiService.getAllUserGroups(userID) }.groups ?: throw Throwable("Error")
    }

    override suspend fun getUserHomePosts(userID: Int, page: Int): List<WallPostDTO> {
        return wrap { apiService.getHomePosts(userID, page = page) }.posts
            ?: throw Throwable("Mapping Error")
    }

    override suspend fun deletePostById(userId: Int, postId: Int): Boolean {
        return wrap { apiService.deletePost(userID = userId, postID = postId) }
    }

    override suspend fun getSearchResult(userId: Int, keyword: String): SearchResultDto {
        return wrap { apiService.getSearch(userID = userId, keyword = keyword) }
    }

    override suspend fun getRequestsToClub(clubId: Int): List<UserDTO> {
        return wrap { apiService.getMemberRequestsToGroup(groupID = clubId) }.requests
            ?: throw Throwable("Error")
    }

    override suspend fun declineClubRequest(userId: Int, memberId: Int, clubId: Int): Boolean {
        return wrap {
            apiService.declineGroupsRequest(userId = userId, memberId = memberId, clubId = clubId)
        }
    }

    override suspend fun acceptClubRequest(userId: Int, memberId: Int, clubId: Int): Boolean {
        return wrap {
            apiService.acceptGroupsRequest(userId = userId, memberId = memberId, clubId = clubId)
        }
    }

    override suspend fun createClub(
        userID: Int,
        groupName: String,
        description: String,
        groupPrivacy: Int,
    ): GroupDTO {
        return wrap {
            apiService.addGroups(
                userID = userID,
                groupName = groupName,
                groupPrivacy = groupPrivacy,
                description = description
            )
        }
    }

    override suspend fun getGroupDetails(userID: Int, groupID: Int): GroupDTO {
        return wrap {
            apiService.getGroupDetails(
                userID = userID, groupID = groupID
            )
        }.group ?: throw Throwable("Error")
    }

    override suspend fun getGroupMembers(groupID: Int): Int {
        return wrap {
            apiService.getGroupMembers(groupID)
        }.count ?: 0
    }

    private suspend fun <T> wrap(function: suspend () -> Response<BaseResponse<T>>): T {
        val response = function()
        return if (response.isSuccessful) {
            when (response.body()?.code) {
                "100" -> response.body()?.payload
                else -> throw Throwable(response.body()?.message.toString())
            } as T
        } else {
            throw Throwable("Network Error")
        }
    }

}