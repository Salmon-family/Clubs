package com.devfalah.repositories

enum class NotificationType(val value: Int) {
    LIKE_PHOTO(1),
    LIKE_POST(2),
    REQUEST_GROUP(3),
    COMMENT_POST(4),
    LIKE_COMMENT_POST(5)
}