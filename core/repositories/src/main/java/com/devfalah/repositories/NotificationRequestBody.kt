package com.devfalah.repositories

import com.google.gson.annotations.SerializedName

object CoreNotificationKeys{
    const val ID_KEY = "id"
    const val FRIEND_ID_KEY = "friendId"
    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val CLICK_ACTION = "click_action"
}

data class NotificationRequestBody(
    val data: NotificationDataModel = NotificationDataModel(),
    val to: String = "",
)



data class NotificationDataModel(
    @SerializedName(CoreNotificationKeys.ID_KEY)
    val id: Int = 0,
    @SerializedName(CoreNotificationKeys.FRIEND_ID_KEY)
    val friendId: Int = 0,
    @SerializedName(CoreNotificationKeys.TITLE)
    val title: String = "",
    @SerializedName(CoreNotificationKeys.DESCRIPTION)
    val description: String = "",
    @SerializedName(CoreNotificationKeys.CLICK_ACTION)
    val clickAction: String = "",
)