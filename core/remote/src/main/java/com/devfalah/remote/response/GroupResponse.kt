package com.devfalah.remote.response


import com.devfalah.repositories.models.group.GroupDTO
import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("groups")
    val groups: List<GroupDTO?>?
)