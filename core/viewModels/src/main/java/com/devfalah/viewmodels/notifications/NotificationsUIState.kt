package com.devfalah.viewmodels.notifications

import com.devfalah.entities.Notification
import com.devfalah.viewmodels.Constants.LIKE_PHOTO
import com.devfalah.viewmodels.Constants.LIKE_POST
import com.devfalah.viewmodels.Constants.NOTHING
import com.devfalah.viewmodels.Constants.REQUEST_GROUP


data class NotificationsUIState(
    val notifications: List<NotificationState> = emptyList(),
    val isLoading: Boolean = false
)

data class NotificationState(
    val id: Int = 0,
    val posterID: Int = 0,
    val posterName: String = "",
    val posterPhoto: String,
    val timeCreated: String = "",
    val type: Int = NOTHING,
    val viewed: Boolean = false,
)

//mapper
fun Notification.toUIState(): NotificationState {
    return NotificationState(
        id = guid,
        timeCreated = timeCreated,
        type = mapType(type),
        viewed = viewed,
        posterID = posterID,
        posterName = posterName,
        posterPhoto = posterImage,
    )
}

private fun mapType(type: String): Int {
    return when (type) {
        "like:entity:file:ossn:aphoto" -> LIKE_PHOTO
        "like:post" -> LIKE_POST
        "group:joinrequest" -> REQUEST_GROUP
        else -> NOTHING
    }
}
