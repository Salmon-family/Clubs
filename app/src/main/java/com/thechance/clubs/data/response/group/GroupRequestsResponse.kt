package com.thechance.clubs.data.response.group


import com.google.gson.annotations.SerializedName

data class GroupRequestsResponse(
    @SerializedName("requests")
    val requests: List<GroupRequestDTO?>?
)