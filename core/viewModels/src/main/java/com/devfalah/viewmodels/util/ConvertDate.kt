package com.devfalah.viewmodels.util

import android.annotation.SuppressLint
import com.devfalah.entities.PublishTime
import com.devfalah.viewmodels.util.DateConverterConstants.DATA_PATTERN
import com.devfalah.viewmodels.util.DateConverterConstants.DAY_AGO
import com.devfalah.viewmodels.util.DateConverterConstants.FULL_DATE
import com.devfalah.viewmodels.util.DateConverterConstants.HOUR_AGO
import com.devfalah.viewmodels.util.DateConverterConstants.JUST_NOW
import com.devfalah.viewmodels.util.DateConverterConstants.MINUTES_AGO
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ConvertDate {

    fun convertTime(createdDate: Long): PublishTime {
        val createdTimeDate = Date(createdDate * 1000).time
        val diffInMillisecond: Long = Date().time - createdTimeDate

        val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisecond)
        val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillisecond)
        val diffInMin = TimeUnit.MILLISECONDS.toMinutes(diffInMillisecond)

        return if (diffInDays > 10) {
            PublishTime(value = convertLongToFullDate(createdTimeDate), FULL_DATE)
        } else if (diffInDays > 0) {
            PublishTime(value = "$diffInDays", DAY_AGO)
        } else if (diffInHours > 0) {
            PublishTime(value = "$diffInHours", HOUR_AGO)
        } else if (diffInMin > 0) {
            PublishTime(value = "$diffInMin", MINUTES_AGO)
        } else {
            PublishTime(value = "", JUST_NOW)
        }
    }

    private fun convertLongToFullDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(DATA_PATTERN)
        return format.format(date)
    }

}

