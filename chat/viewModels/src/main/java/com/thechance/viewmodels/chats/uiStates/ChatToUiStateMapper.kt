package com.thechance.viewmodels.chats.uiStates

import com.thechance.entities.Chat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Chat.toUiState(): ChatUiState {
    return ChatUiState(
        fullName,
        guid,
        icon,
        time.toTime(),
        recentMessage
    )
}

fun Long.toTime(): String {
    val createdTimeDate = Date(this * 1000).time
    val diffInMillisecond: Long = Date().time - createdTimeDate

    val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisecond)
    val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillisecond)
    val diffInMin = TimeUnit.MILLISECONDS.toMinutes(diffInMillisecond)
    val diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisecond)

    return if (diffInDays > 10) {
        val date = Date(this)
        val format = SimpleDateFormat("MMM dd,yyyy HH:mm aa")
        return format.format(date)
    } else if (diffInDays > 0) {
        "$diffInDays days ago"
    } else if (diffInHours > 0) {
        "$diffInHours hours ago"
    } else if (diffInMin > 0) {
        "$diffInMin minutes ago"
    } else {
        "$diffInSec seconds ago"
    }

}