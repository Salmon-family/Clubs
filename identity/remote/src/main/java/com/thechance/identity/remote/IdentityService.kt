package com.thechance.identity.remote

import com.thechance.identity.remote.response.IdentityBaseResponse
import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.UserDataDTO
import retrofit2.Response
import retrofit2.http.*

interface IdentityService {

    @POST("user_authenticate")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<IdentityBaseResponse<Any>>

    @Headers("Content-Type: application/json")
    @POST("user_add")
    suspend fun addUser(
        @Body userData: UserDataDTO
    ): Response<IdentityBaseResponse<AccountDTO>>

}