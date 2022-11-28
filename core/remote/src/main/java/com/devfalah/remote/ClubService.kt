package com.devfalah.remote

import com.devfalah.remote.response.BaseResponse
import com.devfalah.repository.models.UserDTO
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ClubService {

    /**
     * User
     * */

    @POST("user_authenticate")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<BaseResponse<UserDTO>>

}