package com.devfalah.repositories.models.notification


import com.google.gson.annotations.SerializedName

data class NotificationCountDTO(
    @SerializedName("friends")
    val friends: Int?,
    @SerializedName("messages")
    val messages: Int?,
    @SerializedName("notifications")
    val notifications: Int?
)