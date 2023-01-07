package com.thechance.ui

import android.os.Build
import android.text.Html
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.parseHtml(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

fun Long.toTime(showFullDate: Boolean = false): String {
    val createdTimeDate = Date(this * 1000).time
    val diffInMillisecond: Long = Date().time - createdTimeDate
    val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisecond)

    val formattedDate = if (diffInDays > 5) {
        formatDate(createdTimeDate, "d MMM")
    } else if (diffInDays in 5..364) {
        formatDate(createdTimeDate, "yy.MM.dd")
    } else if (diffInDays in 1..5) {
        formatDate(createdTimeDate, "EEE")
    } else {
        formatDate(createdTimeDate, "h:mm a")
    }

    return formattedDate + if (showFullDate && diffInDays >= 1) formatDate(createdTimeDate,
        "/h:mm a") else ""
}

private fun formatDate(time: Long, pattern: String): String {
    val date = Date(time)
    val simpleFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleFormat.format(date)
}