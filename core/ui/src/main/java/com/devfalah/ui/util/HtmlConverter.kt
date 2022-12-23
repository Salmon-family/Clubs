package com.devfalah.ui.util

import androidx.core.text.HtmlCompat

fun String.htmlText(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}