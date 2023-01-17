package com.devfalah.repository.models

import com.google.gson.annotations.SerializedName
object NotificationKeys{
    const val ID_KEY = "id"
    const val FRIEND_ID_KEY = "friendId"
    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val CLICK_ACTION = "click_action"
}

data class NotificationDto(
    val data: NotificationDataModel = NotificationDataModel(),
    val to: String = "",
)



data class NotificationDataModel(
    @SerializedName(NotificationKeys.ID_KEY)
    val id: Int = 0,
    @SerializedName(NotificationKeys.FRIEND_ID_KEY)
    val friendId: Int = 0,
    @SerializedName(NotificationKeys.TITLE)
    val title: String = "",
    @SerializedName(NotificationKeys.DESCRIPTION)
    val description: String = "",
    @SerializedName(NotificationKeys.CLICK_ACTION)
    val clickAction: String = "",
    )