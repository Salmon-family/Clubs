package com.devfalah.viewmodels

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ConvertDate {

    fun convertTime(createdDate: Long): String {
        val createdTimeDate = Date(createdDate * 1000).time
        val diffInMillisecond: Long = Date().time - createdTimeDate

        val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisecond)
        val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillisecond)
        val diffInMin = TimeUnit.MILLISECONDS.toMinutes(diffInMillisecond)
        val diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisecond)

        return if (diffInDays > 10) {
            convertLongToFullDate(createdTimeDate)
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

    private fun convertLongToFullDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("MMM dd,yyyy HH:mm aa")
        return format.format(date)
    }
}
