package com.devfalah.viewmodels.notifications

import com.devfalah.entities.Notification
import com.devfalah.entities.Poster


data class NotificationsUIState (
    val Notifications: List<NotificationState> = emptyList(),
    val isLoading: Boolean = false
)

data class NotificationState(
    val id: Int = 0,
    val poster: Int = 0,
    val timeCreated: Int =0,
    val type: String ="",
    val viewed: String =""
)

data class PosterState(
    val userID: Int = 0,
    val name: String = "",
    val image: String =   "",
)

//mapper
fun Notification.toUIState(): NotificationState {
    return NotificationState(
        id = guid,
        poster= posterGuid,
        timeCreated = timeCreated,
        type =type,
        viewed = viewed
    )
}

fun Poster.toUIState(): PosterState {
    return PosterState(
        userID = guid,
        name = fullName,
        image = icon
    )
}

