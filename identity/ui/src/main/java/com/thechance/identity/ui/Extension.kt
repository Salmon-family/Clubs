package com.thechance.identity.ui

import java.text.SimpleDateFormat
import java.util.*


fun Date.convertToDayMonthYearFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}