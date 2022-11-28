package com.devfalah.remote.response


import com.devfalah.repositories.models.notification.NotificationsDTO
import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<NotificationsDTO?>?,
    @SerializedName("offset")
    val offset: Int?
)