package com.thechance.remote.response


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.notification.NotificationsDTO

data class NotificationResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<NotificationsDTO?>?,
    @SerializedName("offset")
    val offset: Int?
)