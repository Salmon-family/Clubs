package com.devfalah.repositories.models.group


import com.google.gson.annotations.SerializedName

data class GroupWallDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: String?,
    @SerializedName("posts")
    val posts: List<GroupPostDto>?
)