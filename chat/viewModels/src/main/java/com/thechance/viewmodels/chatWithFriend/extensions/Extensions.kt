package com.thechance.viewmodels.chatWithFriend.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("hh:mm aa")
    return format.format(date)
}