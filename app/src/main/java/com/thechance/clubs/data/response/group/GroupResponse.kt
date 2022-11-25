package com.thechance.clubs.data.response.group


import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("groups")
    val groups: List<GroupDTO?>?
)