package com.devfalah.entities

data class Notifications (
        val entity: Boolean,
        val notification: Notification,
        val post: Boolean,
        val poster: Poster
        )

data class Notification(
        val guid: Int = 0,
        val itemGuid: Int =0 ,
        val ownerGuid: Int =0 ,
        val posterGuid: Int =0,
        val subjectGuid: Int =0,
        val timeCreated: Int =0,
        val type: String ="",
        val viewed: String =""
)

data class Poster(
        val fullName: String= "",
        val guid: Int= 0,
        val icon: String = ""
)