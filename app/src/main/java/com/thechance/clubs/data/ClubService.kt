package com.thechance.clubs.data

import com.thechance.clubs.data.response.*
import com.thechance.clubs.data.response.album.AlbumResponse
import com.thechance.clubs.data.response.album.PhotoDetailResponse
import com.thechance.clubs.data.response.album.PhotoDetailsWithUserResponse
import com.thechance.clubs.data.response.album.UserPicture
import com.thechance.clubs.data.response.group.GroupDTO
import com.thechance.clubs.data.response.group.GroupDetailResponse
import com.thechance.clubs.data.response.group.GroupResponse
import com.thechance.clubs.data.response.notification.NotificationCountDTO
import com.thechance.clubs.data.response.notification.NotificationResponse
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


    //////////////////////////////
    @FormUrlEncoded
    @POST("/user_add")
    suspend fun addUser()
//////////////////////

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
    ):Response<BaseResponse<GroupDetailResponse>>

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


    /**
     * message
     * */

    @GET("message_new")
    suspend fun getUnreadMessages()


    /**
     * Album
     * */

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


}