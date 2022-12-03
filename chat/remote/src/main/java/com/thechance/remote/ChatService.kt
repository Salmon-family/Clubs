package com.thechance.remote

import com.thechance.remote.response.BaseResponse
import com.thechance.remote.response.UnreadMessagesResponse
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.MessagesDTO
import retrofit2.Response
import retrofit2.http.*

interface ChatService {
    /**
     * message
     * */

    @GET("message_recent")
    suspend fun getRecentMessages(
        @Query("guid")userID:Int
    ): Response<BaseResponse<ConversationDTO>>

    @POST("message_list")
    suspend fun getConversationWithFriend(
        @Query("to") userID: Int,
        @Query("guid") friendID: Int,
        @Query("markallread") markAsRead: Int = 0,
        @Query("offset") page: Boolean? = null
    ): Response<BaseResponse<ConversationDTO>>


    // 1 to mark as read , 0 if not.
    @POST("message_new")
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

}