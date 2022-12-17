package com.devfalah.entities

data class GroupSearch(
    val groupId: Int,
    val timeCreated: Int,
    val ownerId: Int,
    val description: String,
    val title: String,
    val type: String,
    val subtype: String,
    val membership: String
)
