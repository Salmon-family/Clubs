package com.devfalah.remote.response


import com.devfalah.repositories.models.group.GroupDTO
import com.google.gson.annotations.SerializedName

data class GroupDetailResponse(
    @SerializedName("group")
    val group: GroupDTO?
)