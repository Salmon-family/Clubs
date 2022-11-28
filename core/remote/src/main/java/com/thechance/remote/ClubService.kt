package com.thechance.remote

import com.thechance.remote.response.*
import com.thechance.remote.response.album.*
import com.thechance.remote.response.group.GroupDTO
import com.thechance.remote.response.group.GroupDetailResponse
import com.thechance.remote.response.group.GroupRequestsResponse
import com.thechance.remote.response.group.GroupResponse
import com.thechance.remote.response.message.ConversationDTO
import com.thechance.remote.response.message.MessagesDTO
import com.thechance.remote.response.message.UnreadMessagesResponse
import com.thechance.repository.domainModel.*
import com.thechance.repository.domainModel.album.AlbumDetailsDTO
import com.thechance.repository.domainModel.notification.NotificationCountDTO
import com.thechance.repository.domainModel.notification.NotificationsDTO
import retrofit2.Response
import retrofit2.http.*

interface ClubService {

    /**
     * User
     * */
    @FormUrlEncoded
    @POST("user_authenticate")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<UserDTO>


    @FormUrlEncoded
    @POST("user_details")
    suspend fun getUserDetails(@Field("guid") userID: Int): Response<BaseResponse<UserDTO>>


    @FormUrlEncoded
    @POST("user_edit")
    suspend fun editUser(
        @Field("guid") userID: Int,
        @Field("new_email") email: String,
        @Field("new_gender") gender: String,
        @Field("new_first_name") firstName: String,
        @Field("new_last_name") lastName: String,
        @Field("current_password") currentPassword: String,
        @Field("new_password") newPassword: String = ""
    ): Response<BaseResponse<UserDTO>>

    @FormUrlEncoded
    @POST("user_add")
    suspend fun addUser(
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("email") email: String,
        @Field("reemail") reEmail: String,
        @Field("gender") gender: String,
        @Field("birthdate") birthdate: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<BaseResponse<UserDTO>>

    /**
     * Friends
     * */
    @FormUrlEncoded
    @POST("user_friends")
    suspend fun getUserFriends(@Field("guid") userID: Int)
            : Response<BaseResponse<FriendsResponse>>

    @FormUrlEncoded
    @POST("user_friend_requests")
    suspend fun getUserFriendRequests(@Field("guid") userID: Int)
            : Response<BaseResponse<FriendsRequestResponse>>

    @FormUrlEncoded
    @POST("user_is_friend")
    suspend fun isFriendWith(
        @Field("user_a") userID: Int,
        @Field("user_b") otherUserID: Int
    ): Response<BaseResponse<CheckFriendshipDTO>>

    @FormUrlEncoded
    @POST("user_remove_friend")
    suspend fun removeFriend(
        @Field("user_a") userID: Int,
        @Field("user_b") otherUserID: Int
    ): Response<BaseResponse<CheckFriendshipDTO>>


    /**
     * posts
     * */
    @FormUrlEncoded
    @POST("like_add")
    suspend fun addLike(
        @Field("uguid") userID: Int,
        @Field("subject_guid") postID: Int,
        @Field("type") type: LikeType
    ): Response<BaseResponse<ReactionDTO>>

    @FormUrlEncoded
    @POST("unlike_set")
    suspend fun removeLike(
        @Field("uguid") userID: Int,
        @Field("subject_guid") postID: Int,
        @Field("type") type: LikeType
    ): Response<BaseResponse<ReactionDTO>>


    /**
     * Comment
     * */
    @FormUrlEncoded
    @POST("comment_add")
    suspend fun addComment(
        @Field("uguid") userID: Int,
        @Field("subject_guid") postID: Int,
        @Field("comment") comment: String,
        @Field("image_file") imageFile: Array<String>? = null,
    ): Response<BaseResponse<CommentResponse>>

    @FormUrlEncoded
    @POST("comment_delete")
    suspend fun deleteComment(
        @Field("guid") userID: Int,
        @Field("id") commentID: Int
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("comment_edit")
    suspend fun editComment(
        @Field("guid") commentID: Int,
        @Field("comment") comment: String
    ): Response<BaseResponse<SuccessDTO>>

    @FormUrlEncoded
    @POST("comments_list")
    suspend fun getComments(
        @Field("uguid") userID: Int,
        @Field("type") type: LikeType,
        @Field("guid") postID: Int,
        @Field("page_limit") page: Int? = null,
        @Field("limit") limit: Int = 5
    ): Response<BaseResponse<CommentsResponse>>

    /**
     * Group
     * */

    @FormUrlEncoded
    @POST("groups_members")
    suspend fun getGroupMembers(
        @Field("group_guid") groupID: Int,
        @Field("offset") page: Int? = null
    ): Response<BaseResponse<GroupMembersResponse>>

    @FormUrlEncoded
    @POST("groups_add")
    suspend fun addGroups(
        @Field("guid") userID: Int,
        @Field("name") groupName: String,
        @Field("privacy") groupPrivacy: Int
    ): Response<BaseResponse<GroupDTO>>

    @FormUrlEncoded
    @POST("groups_edit")
    suspend fun editGroups(
        @Field("group_guid") groupID: Int,
        @Field("uguid") groupOwnerID: Int,
        @Field("groupname") groupName: String,
        @Field("groupdesc") groupDescription: String? = null,
        @Field("membership") groupPrivacy: Int? = null
    ): Response<BaseResponse<Boolean>>

    @GET("groups_user_memberof")
    suspend fun getAllUserGroups(
        @Query("guid") userID: Int
    ): Response<BaseResponse<GroupResponse>>

    @GET("groups_view")
    suspend fun getGroupDetails(
        @Query("guid") userID: Int,
        @Query("group_guid") groupID: Int
    ): Response<BaseResponse<GroupDetailResponse>>

    @GET("groups_requests")
    suspend fun getMemberRequestsToGroup(
        @Query("group_guid") groupID: Int
    ): Response<BaseResponse<GroupRequestsResponse>>

    /**
     * notification
     * */
    @GET("notifications_list_user")
    suspend fun getNotifications(
        @Query("owner_guid") userID: Int,
        @Query("types") type: String? = null,
        @Query("offset") page: Int? = null
    ): Response<BaseResponse<NotificationResponse>>

    @GET("notifications_count")
    suspend fun getCountsOfNotificationsORMessagesORFriendRequest(
        @Query("guid") userID: Int,
        @Query("types") types: String? = null
    ): Response<BaseResponse<NotificationCountDTO>>

    @FormUrlEncoded
    @POST("notifications_mark_viewed")
    suspend fun markNotificationsAsViewed(
        @Field("notification_guid") notificationID: Int
    ): Response<BaseResponse<NotificationsDTO>>

    /**
     * message
     * */

    @GET("message_recent")
    suspend fun getRecentMessages(
        @Query("guid")userID:Int
    ):Response<BaseResponse<ConversationDTO>>

    @GET("message_list")
    suspend fun getConversationWithFriend(
        @Query("to") userID: Int,
        @Query("guid") friendID: Int,
        @Query("markallread") markAsRead: Int = 0,
        @Query("offset") page: Int? = null
    ): Response<BaseResponse<ConversationDTO>>


    // 1 to mark as read , 0 if not.
    @GET("message_new")
    suspend fun getUnreadMessages(
        @Query("to") userID: Int,
        @Query("from") friendID: Int,
        @Query("markallread") markAsRead: Int = 0
    ): Response<BaseResponse<UnreadMessagesResponse>>

    @FormUrlEncoded
    @POST("message_add")
    suspend fun sendMessage(
        @Field("from") userID: Int,
        @Field("to") friendID: Int,
        @Field("message") message: String
    ): Response<BaseResponse<MessagesDTO>>

    /**
     * Album
     * */

    @GET("photos_list_albums")
    suspend fun getAlbums(
        @Query("guid") userID: Int,
        @Query("uguid") albumGuid: Int
    ): Response<BaseResponse<AlbumsResponse>>


    @GET("photos_list_profile_cover")
    suspend fun getUserProfileAlbum(
        @Query("guid") userId: Int,
        @Query("type") userPictureType: UserPictureType
    ): Response<BaseResponse<UserPicture>>

    @GET("photos_list")
    suspend fun getAllPhotosInAlbum(
        @Query("album_guid") albumID: Int
    ): Response<BaseResponse<AlbumResponse>>

    //Get a details either for profile or cover photo
    @GET("photos_view_profile")
    suspend fun getProfileOrCoverPhotoDetails(
        @Query("uguid") UserID: Int,
        @Query("photo_guid") photoID: Int
    ): Response<BaseResponse<PhotoDetailResponse>>


    //Get a standard album photo details (other than profile/cover)
    @GET("photos_view")
    suspend fun getPhotoDetails(
        @Query("uguid") UserID: Int,
        @Query("photo_guid") photoID: Int
    ): Response<BaseResponse<PhotoDetailsWithUserResponse>>


    //Delete a specific cover photo.
    @FormUrlEncoded
    @POST("photos_delete_profile")
    suspend fun deleteSpecificCoverPhoto(
        @Field("guid") ownerID: Int,
        @Field("photoid") photoID: Int
    ): Response<BaseResponse<Any>>

    //Delete a specific profile photo.
    @FormUrlEncoded
    @POST("photos_delete_profile")
    suspend fun deleteSpecificProfilePhoto(
        @Field("guid") ownerID: Int,
        @Field("photoid") photoID: Int
    ): Response<BaseResponse<PhotoDetailsWithUserResponse>>

    //Delete photo other than profile or cover photo.
    @FormUrlEncoded
    @POST("photos_delete")
    suspend fun deletePhoto(
        @Field("guid") ownerID: Int,
        @Field("photoid") photoID: Int
    ): Response<BaseResponse<Any>>


    @FormUrlEncoded
    @POST("photos_album_create")
    suspend fun createAlbum(
        @Field("guid") userID: Int,
        @Field("title") albumTitle: String,
        // 3 for Friends only, 2 for public
        @Field("privacy") albumPrivacy: Int
    ): Response<BaseResponse<AlbumDetailsDTO>>


    /**
     * Wall
     * */

    @FormUrlEncoded
    @POST("wall_delete")
    suspend fun deletePost(
        @Field("guid") userID: Int,
        @Field("post_guid") postID: Int
    ): Response<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("wall_add")
    suspend fun addPostOnWallFriendOrGroup(
        @Field("poster_guid") userId: Int,
        @Field("owner_guid") friendOrGroupID: Int,
        @Field("type") type: PostType,
        @Field("post") post: String?,
        @Field("friends") taggedFriends: List<Int>,
        @Field("location") location: String,
        //3 for Friends only, 2 for public.
        @Field("privacy") privacy: Int = 2,
        @Field("ossn_photo") photo: String? = null
    )

    @GET("wall_view")
    suspend fun getWallPost(
        @Query("guid") userID: Int,
        @Query("post_guid") postID: Int
    ): Response<BaseResponse<WallPostDTO>>

    @GET("wall_list_user")
    suspend fun getAllWallPosts(
        @Query("guid") userID: Int,
        @Query("uguid") friendID: Int,
        @Query("offset") page: Int? = null,
        @Query("count") pageSize: Int? = null
    ): Response<BaseResponse<FriendWallDTO>>
}