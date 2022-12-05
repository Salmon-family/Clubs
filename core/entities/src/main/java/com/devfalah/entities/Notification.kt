package com.devfalah.entities

data class Notification(
    val entity: Boolean,
    val post: Boolean,
    val guid: Int ,
    val itemGuid: Int ,
    val ownerGuid: Int ,
    val subjectGuid: Int ,
    val timeCreated: String ,
    val type: Int ,
    val viewed: Boolean = false,
    val posterID: Int ,
    val posterName: String  ,
    val posterImage: String
)
