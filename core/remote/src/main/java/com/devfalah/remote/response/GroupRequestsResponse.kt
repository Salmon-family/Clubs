package com.devfalah.remote.response


import com.devfalah.repositories.models.UserDTO
import com.google.gson.annotations.SerializedName

data class GroupRequestsResponse(
    @SerializedName("requests")
    val requests: List<UserDTO>?
)