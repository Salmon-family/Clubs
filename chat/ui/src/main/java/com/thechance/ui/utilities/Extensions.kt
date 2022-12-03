package com.thechance.ui.utilities

import java.text.SimpleDateFormat
import java.util.*



fun Int.convertIntToTime(): String {
    val date = Date(this.toLong())
    val format = SimpleDateFormat("hh:mm")
    return format.format(date)
}

