package com.devfalah.remote.util

enum class LikeType(val value: String) {
    POST("post"),
    ANNOTATION("annotation")
}

enum class GroupPrivacy(val value: Int) {
    CLOSED(1),
    PUBLIC(2)
}

enum class PostType(val value: String) {
    USER("user"),
    GROUP("group")
}