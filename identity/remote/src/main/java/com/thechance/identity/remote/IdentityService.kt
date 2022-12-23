package com.thechance.identity.remote

import com.thechance.identity.remote.response.IdentityBaseResponse
import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.ClubDto
import com.thechance.identity.repositories.models.UserDTO
import retrofit2.Response
import retrofit2.http.*


interface IdentityService {

    @FormUrlEncoded
    @POST("user_authenticate")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<IdentityBaseResponse<UserDTO>>


    @FormUrlEncoded
    @POST("user_add")
    suspend fun addUser(
        @Field("fullname") fullName: String,
        @Field("job_title") jobTitle: String,
        @Field("fcm_token") fcmToken: String,
        @Field("email") email: String,
        @Field("reemail") reEmail: String,
        @Field("gender") gender: String,
        @Field("birthdate") birthdate: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<IdentityBaseResponse<String>>

    @POST("groups_join")
    suspend fun joinClub(
        @Query("group_guid") clubId: Int,
        @Query("guid") userId: Int
    ): Response<IdentityBaseResponse<ClubDto>>

    @POST("groups_request_accept")
    suspend fun acceptJoiningRequest(
        @Query("group_guid") clubId: Int,
        @Query("guid") userId: Int,
        @Query("uguid") clubOwnerId: Int
    ): Response<IdentityBaseResponse<Boolean>>
}