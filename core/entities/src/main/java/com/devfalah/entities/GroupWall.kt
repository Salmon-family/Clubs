package com.devfalah.entities

data class GroupWall(
    val count: Int,
    val offset: String,
    val post: List<GroupWallPost>
)
