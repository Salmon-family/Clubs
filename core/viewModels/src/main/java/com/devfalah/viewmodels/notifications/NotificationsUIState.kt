package com.devfalah.viewmodels.notifications

import com.devfalah.entities.Notification


data class NotificationsUIState(
    val userId: Int = 0,
    val notifications: List<NotificationState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class NotificationState(
    val id: Int = 0,
    val publisherId: Int = 0,
    val publisherName: String = "",
    val publisherImageUrl: String,
    val timeCreated: String = "",
    val type: Int = 0,
    val viewed: Boolean = false,
    val subjectId: Int = 0,
)

//mapper
fun Notification.toUIState(): NotificationState {
    return NotificationState(
        id = guid,
        timeCreated = timeCreated,
        type = type,
        viewed = viewed,
        publisherId = ownerGuid,
        publisherName = posterName,
        publisherImageUrl = posterImage,
        subjectId = subjectGuid
    )
}


fun List<Notification>.toUIState() = map { it.toUIState() }