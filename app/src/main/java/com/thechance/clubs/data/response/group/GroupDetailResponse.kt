package com.thechance.clubs.data.response.group


import com.google.gson.annotations.SerializedName

data class GroupDetailResponse(
    @SerializedName("group")
    val group: GroupDTO?
)