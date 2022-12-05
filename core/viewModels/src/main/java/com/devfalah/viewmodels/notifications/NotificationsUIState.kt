package com.devfalah.viewmodels.notifications

import com.devfalah.entities.Notification


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
    val type: Int = 0,
    val viewed: Boolean = false,
)

//mapper
fun Notification.toUIState(): NotificationState {
    return NotificationState(
        id = guid,
        timeCreated = timeCreated,
        type = type,
        viewed = viewed,
        posterID = posterID,
        posterName = posterName,
        posterPhoto = posterImage,
    )
}


fun List<Notification>.toUIState() = map { it.toUIState() }