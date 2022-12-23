package com.devfalah.remote

import com.devfalah.remote.response.*
import com.devfalah.repositories.models.*
import com.devfalah.repositories.models.album.AlbumDetailsDTO
import com.devfalah.repositories.models.group.GroupDTO
import com.devfalah.repositories.models.notification.NotificationCountDTO
import com.devfalah.repositories.models.notification.NotificationsDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ClubService {

    @FormUrlEncoded
    @POST("user_details")
    suspend fun getUserDetails(@Field("guid") userID: Int): Response<BaseResponse<UserDTO>>

    @FormUrlEncoded
    @POST("user_edit")
    suspend fun editUserDetails(
        @Field("guid") userID: Int,
        @Field("new_full_name") name: String,
        @Field("new_job_title") title: String,
        @Field("new_email") email: String,
        @Field("current_password") currentPassword: String,
        @Field("new_password") newPassword: String = "",
    ): Response<BaseResponse<UserDTO>>


    /**
     * Friends
     * */
    @FormUrlEncoded
    @POST("user_friends")
    suspend fun getUserFriends(
        @Field("guid") userID: Int,
        @Field("offset") page: Int
    ): Response<BaseResponse<FriendsDTO>>

    @FormUrlEncoded
    @POST("user_friend_requests")
    suspend fun getUserFriendRequests(@Field("guid") userID: Int)
            : Response<BaseResponse<FriendsRequestResponse>>

    @FormUrlEncoded
    @POST("user_is_friend")
    suspend fun isFriendWith(
        @Field("user_a") userID: Int,
        @Field("user_b") otherUserID: Int
    ): Response<BaseResponse<FriendshipDTO>>

    @FormUrlEncoded
    @POST("user_remove_friend")
    suspend fun removeFriend(
        @Field("user_a") userID: Int,
        @Field("user_b") otherUserID: Int
    ): Response<BaseResponse<FriendshipDTO>>

    @FormUrlEncoded
    @POST("user_add_friend")
    suspend fun addFriendRequest(
        @Field("user_a") userID: Int,
        @Field("user_b") otherUserID: Int
    ): Response<BaseResponse<FriendshipDTO>>

    /**
     * posts
     * */
    @FormUrlEncoded
    @POST("like_add")
    suspend fun addLike(
        @Field("uguid") userID: Int,
        @Field("subject_guid") postID: Int,
        @Field("type") type: String
    ): Response<BaseResponse<ReactionDTO>>

    @FormUrlEncoded
    @POST("unlike_set")
    suspend fun removeLike(
        @Field("uguid") userID: Int,
        @Field("subject_guid") postID: Int,
        @Field("type") type: String
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
        @Field("type") type: String,
        @Field("guid") postID: Int,
        @Field("offset") page: Int = 1,
        @Field("limit") limit: Int = 20
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
        @Field("privacy") groupPrivacy: Int,
        @Field("description") description: String,
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

    @GET("groups_request_decline")
    suspend fun declineGroupsRequest(
        @Query("group_guid") clubId: Int,
        @Query("guid") memberId: Int,
        @Query("uguid") userId: Int
    ): Response<BaseResponse<Boolean>>

    @GET("groups_request_accept")
    suspend fun acceptGroupsRequest(
        @Query("group_guid") clubId: Int,
        @Query("guid") memberId: Int,
        @Query("uguid") userId: Int
    ): Response<BaseResponse<Boolean>>

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

    @Multipart
    @POST("wall_add")
    suspend fun addPostWithImage(
        @Part("poster_guid") userId: RequestBody,
        @Part("owner_guid") friendOrGroupID: RequestBody,
        @Part("type") type: RequestBody,
        @Part("post") post: RequestBody,
        @Part("privacy") privacy: RequestBody,
        @Part file: MultipartBody.Part,
    ): Response<BaseResponse<WallPostDTO>>

    @FormUrlEncoded
    @POST("wall_add")
    suspend fun addPostOnWallFriendOrGroup(
        @Field("poster_guid") userId: Int,
        @Field("owner_guid") friendOrGroupID: Int,
        @Field("type") type: String,
        @Field("post") post: String,
        @Field("friends") taggedFriends: List<Int> = emptyList(),
        @Field("location") location: String = "",
        @Field("privacy") privacy: Int = 2,
        @Field("ossn_photo") photo: String? = null
    ): Response<BaseResponse<WallPostDTO>>

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
    ): Response<BaseResponse<ProfilePostResponse>>

    @GET("wall_list_home")
    suspend fun getHomePosts(
        @Query("guid") userID: Int,
        @Query("offset") page: Int? = null,
        @Query("count") pageSize: Int? = null
    ): Response<BaseResponse<ProfilePostResponse>>

    @Multipart
    @POST("photos_profile_add")
    suspend fun addProfilePicture(
        @Part("guid") userId: RequestBody,
        @Part file: MultipartBody.Part,
    ): Response<BaseResponse<UserDTO>>

    @GET("my_custom_end_point")
    suspend fun getSearch(
        @Query("guid") userID: Int,
        @Query("keyword") keyword: String,
    ): Response<BaseResponse<SearchResultDto>>

}