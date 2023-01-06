package com.thechance.remote.api

import com.devfalah.repository.models.ChatDTO
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.FriendsResponse
import com.devfalah.repository.models.UserDTO
import com.thechance.remote.response.BaseResponse
import retrofit2.Response
import retrofit2.http.*

interface ChatService {

    @GET("message_recent")
    suspend fun getRecentMessages(
        @Query("guid") userID: Int,
        @Query("offset") page: Int = 1,
    ): Response<BaseResponse<ConversationDTO>>

    @POST("message_list")
    suspend fun getConversationWithFriend(
        @Query("to") userID: Int,
        @Query("guid") friendID: Int,
        @Query("markallread") markAsRead: Int = 0,
        @Query("offset") page: Int,
    ): Response<BaseResponse<ConversationDTO>>

    @FormUrlEncoded
    @POST("message_add")
    suspend fun sendMessage(
        @Field("from") userID: Int,
        @Field("to") friendID: Int,
        @Field("message") message: String,
    ): Response<BaseResponse<ChatDTO>>


    @FormUrlEncoded
    @POST("user_details")
    suspend fun getUserDetails(@Field("guid") userID: Int): Response<BaseResponse<UserDTO>>

    @GET("user_friends")
    suspend fun getAllFriends(
        @Query("guid") userID: Int,
    ): Response<BaseResponse<FriendsResponse>>
}