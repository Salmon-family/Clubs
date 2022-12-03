package com.thechance.ui.extensions


fun String.minimizeLongString(size: Int): String {
    return if (this.length > size) {
        this.substring(0, size) + "..."
    } else {
        this
    }
}