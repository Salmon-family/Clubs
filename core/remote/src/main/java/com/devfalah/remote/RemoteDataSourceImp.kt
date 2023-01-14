package com.devfalah.remote


import com.devfalah.remote.response.BaseResponse
import com.devfalah.repositories.models.GroupMembersDTO
import com.devfalah.remote.util.Constants.IMAGE_FILE
import com.devfalah.remote.util.Constants.POST_IMAGE_DESCRIPTION
import com.devfalah.remote.util.Constants.PROFILE_IMAGE_DESCRIPTION
import com.devfalah.remote.util.Constants.TYPE
import com.devfalah.remote.util.LikeType
import com.devfalah.remote.util.PostType
import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repositories.models.*
import com.devfalah.repositories.models.album.AlbumDTO
import com.devfalah.repositories.models.friends.FriendDTO
import com.devfalah.repositories.models.friends.FriendsDTO
import com.devfalah.repositories.models.friends.FriendshipDTO
import com.devfalah.repositories.models.group.GroupDTO
import com.devfalah.repositories.models.group.GroupWallDto
import com.devfalah.repositories.models.notification.NotificationsDTO
import com.devfalah.repositories.models.post.WallPostDTO
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService: ClubService,
) : RemoteDataSource {

    override suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return wrap { apiService.removeFriend(userID, friendRequestID) }.success
            ?: throw Throwable("Mapping Error")
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

    override suspend fun getProfilePostsPager(userID: Int, profileUserID: Int, page: Int)
            : List<WallPostDTO> {
        return wrap { apiService.getAllWallPosts(userID, profileUserID, page = page) }.posts
            ?: throw Throwable("Mapping Error")
    }

    override suspend fun setLikeOnPost(userID: Int, postId: Int): ReactionDTO {
        return wrap {
            apiService.addLike(userID = userID, postID = postId, type = LikeType.POST.value)
        }
    }

    override suspend fun removeLikeOnPost(userID: Int, postId: Int): ReactionDTO {
        return wrap {
            apiService.removeLike(userID = userID, postID = postId, type = LikeType.POST.value)
        }
    }


    override suspend fun setLikeOnComment(userID: Int, commentId: Int): ReactionDTO {
        return wrap {
            apiService.addLike(
                userID = userID,
                postID = commentId,
                type = LikeType.ANNOTATION.value
            )
        }
    }

    override suspend fun removeLikeOnComment(userID: Int, commentId: Int): ReactionDTO {
        return wrap {
            apiService.removeLike(
                userID = userID,
                postID = commentId,
                type = LikeType.ANNOTATION.value
            )
        }
    }

    override suspend fun getFriendShipStatus(userID: Int, friendID: Int): FriendshipDTO {
        return wrap { apiService.isFriendWith(userID = userID, otherUserID = friendID) }
    }

    override suspend fun addProfilePicture(userID: Int, file: File): UserDTO {
        val extension = if (file.extension.equals("jpg", true)) {
            "jpeg"
        } else {
            file.extension
        }
        val requestBody = file.asRequestBody("$IMAGE_FILE/${extension}".toMediaTypeOrNull())
        val part =
            MultipartBody.Part.createFormData(PROFILE_IMAGE_DESCRIPTION, file.name, requestBody)
        val id = RequestBody.create(TYPE.toMediaTypeOrNull(), userID.toString())
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
        userID: Int, groupName: String, description: String, groupPrivacy: Int,
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
        return wrap { apiService.getGroupDetails(userID = userID, groupID = groupID) }.group
            ?: throw Throwable("Error")
    }

    override suspend fun getGroupMembers(groupID: Int, page: Int): GroupMembersDTO {
        return wrap { apiService.getGroupMembers(groupID, page = page) }
    }

    override suspend fun getGroupWallList(userID: Int, groupID: Int, page: Int): GroupWallDto {
        return wrap {
            apiService.getGroupWallList(userID = userID, groupID = groupID, page = page)
        }
    }

    override suspend fun joinClub(clubId: Int, userId: Int): GroupDTO {
        return wrap {
            apiService.joinClub(clubId, userId)
        }
    }

    override suspend fun unJoinClub(clubId: Int, userId: Int): GroupDTO {
        return wrap {
            apiService.unJoinClub(clubId, userId)
        }
    }

    override suspend fun declineClub(clubId: Int, memberId: Int, userId: Int): Boolean {
        return apiService.declineGroupsRequest(clubId, memberId, userId).body()?.payload
            ?: throw Throwable("Error")
    }

    override suspend fun editUserInformation(user: UserInfo): UserDTO {
        return wrap {
            apiService.editUserDetails(
                userID = user.id,
                name = user.name,
                title = user.title,
                email = user.email,
                currentPassword = user.password,
                newPassword = user.newPassword
            )
        }
    }

    override suspend fun publishPostUserWall(
        userId: Int, publishOnId: Int, postContent: String, privacy: Int
    ): WallPostDTO {
        return wrap {
            apiService.addPostOnWallFriendOrGroup(
                userId = userId,
                friendOrGroupID = publishOnId,
                type = getPublishType(userId = userId, publishOnId = publishOnId).value,
                post = postContent,
                privacy = privacy
            )
        }
    }

    override suspend fun publishPostWithImage(
        userId: Int, publishOnId: Int, postContent: String, privacy: Int, imageFile: File
    ): WallPostDTO {
        val extension = if (imageFile.extension.equals("jpg", true)) {
            "jpeg"
        } else {
            imageFile.extension
        }
        val requestBody =
            imageFile.asRequestBody("$IMAGE_FILE/${extension}".toMediaTypeOrNull())
        val part =
            MultipartBody.Part.createFormData(POST_IMAGE_DESCRIPTION, imageFile.name, requestBody)

        val id = userId.toString().toRequestBody(TYPE.toMediaTypeOrNull())
        val publishOn = publishOnId.toString().toRequestBody(TYPE.toMediaTypeOrNull())
        val type =
            getPublishType(userId, publishOnId).value.toRequestBody(TYPE.toMediaTypeOrNull())
        val postPrivacy = privacy.toString().toRequestBody(TYPE.toMediaTypeOrNull())
        val postText = postContent.toRequestBody(TYPE.toMediaTypeOrNull())

        val x = apiService.addPostWithImage(
            userId = id,
            friendOrGroupID = publishOn,
            type = type,
            privacy = postPrivacy,
            post = postText,
            file = part
        )
        println("TESTTTT " + x.body()?.payload.toString())
        return x.body()?.payload ?: throw Throwable("Error")

    }

    private fun getPublishType(userId: Int, publishOnId: Int): PostType {
        return if (userId == publishOnId) {
            PostType.USER
        } else {
            PostType.GROUP
        }
    }

    override suspend fun editClub(
        clubId: Int,
        userID: Int,
        clubName: String,
        description: String,
        clubPrivacy: Int,
    ): Boolean {
        return wrap {
            apiService.editGroups(
                groupID = clubId,
                groupName = clubName,
                groupOwnerID = userID,
                groupDescription = description,
                groupPrivacy = clubPrivacy
            )
        }
    }

    override suspend fun deleteComment(userId: Int, commentId: Int): Boolean {
        return wrap { apiService.deleteComment(userID = userId, commentID = commentId) }
    }

    override suspend fun editComment(commentId: Int, comment: String): Boolean {
        val response = wrap { apiService.editComment(commentID = commentId, comment = comment) }
        return !response.success.isNullOrEmpty()
    }

    override suspend fun markNotificationMarkedAsViewed(notificationId: Int) {
        wrap { apiService.markNotificationsAsViewed(notificationId) }
    }

    //region postComments

    override suspend fun getPostComments(postId: Int, userId: Int, page: Int): List<CommentDto> {
        return apiService.getComments(
            userID = userId,
            postID = postId,
            type = LikeType.POST.value,
            page = page
        ).body()?.payload?.comments ?: emptyList()
    }

    override suspend fun getPostByID(postId: Int, userID: Int): WallPostDTO {
        return try {
            wrap { apiService.getWallPost(userID = userID, postID = postId) }
        } catch (t: Throwable) {
           if (t.message.toString().contains("host")){
               throw t
           }else{
               throw Throwable("NotFound")
           }
        }
    }

    override suspend fun addComment(userId: Int, postId: Int, comment: String): AddedCommentDTO {
        return wrap { apiService.addComment(userID = userId, postID = postId, comment = comment) }
    }

    //endregion

    override suspend fun getUserGroups(userId: Int): List<GroupDTO> {
        return wrap { apiService.getAllUserGroups(userId) }.groups
            ?: throw Throwable("empty groups")
    }

    private suspend fun <T : Any> wrap(function: suspend () -> Response<BaseResponse<T>>): T {
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