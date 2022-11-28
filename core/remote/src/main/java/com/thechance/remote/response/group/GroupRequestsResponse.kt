package com.thechance.remote.response.group


import com.google.gson.annotations.SerializedName

data class GroupRequestsResponse(
    @SerializedName("requests")
    val requests: List<com.thechance.remote.response.group.GroupRequestDTO?>?
)