package com.devfalah.repositories.models.notification


import com.google.gson.annotations.SerializedName

data class NotificationsDTO(
    @SerializedName("entity")
    val entity: Boolean?,
    @SerializedName("notification")
    val notification: NotificationDTO?,
    @SerializedName("post")
    val post: Boolean?,
    @SerializedName("poster")
    val poster: PosterDTO?
)