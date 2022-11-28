package com.thechance.remote.response.group


import com.google.gson.annotations.SerializedName

data class GroupDetailResponse(
    @SerializedName("group")
    val group: com.thechance.remote.response.group.GroupDTO?
)