package com.thechance.viewmodels.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertLongToTime(): String {
    val date = Date(this*1000)
    val format = SimpleDateFormat("hh:mm aa")
    return format.format(date)
}