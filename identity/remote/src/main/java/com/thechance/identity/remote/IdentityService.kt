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
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("email") email: String,
        @Field("reemail") reEmail: String,
        @Field("gender") gender: String,
        @Field("birthdate") birthdate: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<IdentityBaseResponse<AccountDTO>>

    @POST("groups_join")
    suspend fun joinClub(
        @Query("group_guid") clubId: Int,
        @Query("guid") userId: Int
    ): Response<IdentityBaseResponse<ClubDto>>
}