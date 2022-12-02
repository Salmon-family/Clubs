package com.devfalah.entities

data class Notifications(
    val entity: Boolean,
    val notification: Notification,
    val post: Boolean,
)

data class Notification(
    val guid: Int = 0,
    val itemGuid: Int = 0,
    val ownerGuid: Int = 0,
    val subjectGuid: Int = 0,
    val timeCreated: String = "",
    val type: String = "",
    val viewed: Boolean = false,
    val posterID: Int = 0,
    val posterName: String = "",
    val posterImage: String = ""
)
