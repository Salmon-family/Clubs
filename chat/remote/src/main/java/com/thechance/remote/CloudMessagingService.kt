package com.thechance.remote

import com.devfalah.repository.models.NotificationDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CloudMessagingService {

    companion object {
        const val BASE_URL = "https://fcm.googleapis.com"
        private const val CONTENT_TYPE = "application/json"
        private const val key = "AAAABftyyQ4:APA91bFX_vdfUmXtDmlYhojZd58codLEKM2XusxkLL4Wp8RclgiDuo2SfyiefaVncXdMOO5fTrnF4roQz4jw0rKw-fV_c9Iqso8ceOuQH14WAkp5brSRD03IT-wamq09yzlLxvCw4JN7"
    }

    @POST("fcm/send")
    @Headers("Authorization: key=$key", "Content-Type:$CONTENT_TYPE")
    suspend fun postNotification(
        @Body notification: NotificationDto
    ): Response<ResponseBody>
}