package com.devfalah.remote.response


import com.devfalah.repositories.models.UserDTO
import com.google.gson.annotations.SerializedName

data class GroupMembersResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("members")
    val members: List<UserDTO>?,
    @SerializedName("offset")
    val offset: Int?
)