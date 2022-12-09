package com.devfalah.repository.models

import com.google.gson.annotations.SerializedName
object NotificationDtoKeys{
    const val ID_KEY = "id"
    const val FRIEND_ID_KEY = "friendId"
    const val MESSAGE_TEXT_KEY = "messageText"
    const val TIME_KEY = "time"

}

data class NotificationDto(
    val data: NotificationDataModel = NotificationDataModel(),
    val to: String = ""
)

data class NotificationDataModel(
    @SerializedName(NotificationDtoKeys.ID_KEY)
    val id: Int = 0,
    @SerializedName(NotificationDtoKeys.FRIEND_ID_KEY)
    val friendId: Int = 0,
    @SerializedName(NotificationDtoKeys.MESSAGE_TEXT_KEY)
    val messageText: String = "",
    @SerializedName(NotificationDtoKeys.TIME_KEY)
    val time: String = "",
    )