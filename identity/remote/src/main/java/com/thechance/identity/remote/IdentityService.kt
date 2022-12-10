package com.thechance.identity.remote

import com.thechance.identity.remote.response.IdentityBaseResponse
import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.UserDTO
import com.thechance.identity.repositories.models.UserDataDTO
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface IdentityService {

    @POST("user_authenticate")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<IdentityBaseResponse<UserDTO>>


    @POST("user_add")
    suspend fun addUser(
        @Body userDTO: UserDataDTO
    ): Response<IdentityBaseResponse<AccountDTO>>

}