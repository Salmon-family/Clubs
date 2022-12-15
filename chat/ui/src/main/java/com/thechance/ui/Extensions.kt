package com.thechance.ui

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Long.toTime(): String {
    val createdTimeDate = Date(this * 1000).time
    val diffInMillisecond: Long = Date().time - createdTimeDate
    val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisecond)


    return if (diffInDays > 7) {
        formatDate(createdTimeDate, "d MMM")
    }
    else if (diffInDays in 7..364){
        formatDate(createdTimeDate,"yy.MM.dd")
    }
    else if (diffInDays in 1..7) {
        formatDate(createdTimeDate, "EEE")
    } else {
        formatDate(createdTimeDate, "h:mm a")
    }
}

private fun formatDate(time: Long, pattern: String): String {
    val date = Date(time)
    val simpleFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleFormat.format(date)
}