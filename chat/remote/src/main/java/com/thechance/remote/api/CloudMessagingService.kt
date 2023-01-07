package com.thechance.remote.api

import com.devfalah.repository.models.NotificationDto
import com.thechance.remote.BuildConfig
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CloudMessagingService {

    companion object {
        const val BASE_URL = "https://fcm.googleapis.com"
        private const val CONTENT_TYPE = "application/json"
        private const val key = BuildConfig.FIREBASE_API_KEY
    }

    @POST("fcm/send")
    @Headers("Authorization: key=$key", "Content-Type:$CONTENT_TYPE")
    suspend fun postNotification(
        @Body notification: NotificationDto,
    ): Response<ResponseBody>
}