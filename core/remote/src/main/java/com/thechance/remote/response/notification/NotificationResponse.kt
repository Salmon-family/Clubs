package com.thechance.remote.response.notification


import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<com.thechance.remote.response.notification.NotificationsDTO?>?,
    @SerializedName("offset")
    val offset: Int?
)