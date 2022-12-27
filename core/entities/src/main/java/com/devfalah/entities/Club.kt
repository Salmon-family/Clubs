package com.devfalah.entities

data class Club(
    val id: Int,
    val ownerId: Int,
    val isOwner: Boolean,
    val name: String,
    val privacy: String,
    val description: String,
    val isMember: Boolean,
    val requestExists: Boolean,
)