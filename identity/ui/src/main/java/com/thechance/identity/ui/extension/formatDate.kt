package com.thechance.identity.ui.extension

import java.text.SimpleDateFormat
import java.util.*


fun Date.convertToDayMonthYearFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}