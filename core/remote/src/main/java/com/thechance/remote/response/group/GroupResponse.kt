package com.thechance.remote.response.group


import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("groups")
    val groups: List<com.thechance.remote.response.group.GroupDTO?>?
)