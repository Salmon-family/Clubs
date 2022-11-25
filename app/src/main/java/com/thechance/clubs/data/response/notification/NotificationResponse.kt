package com.thechance.clubs.data.response.notification


import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<NotificationsDTO?>?,
    @SerializedName("offset")
    val offset: Int?
)