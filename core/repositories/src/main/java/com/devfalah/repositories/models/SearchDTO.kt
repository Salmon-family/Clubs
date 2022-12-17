package com.devfalah.repositories.models

import com.google.gson.annotations.SerializedName

data class SearchDTO(
    @SerializedName("users")
    val users: List<UserDTO>?,
    @SerializedName("groups")
    val groups: List<GroupSearchDTO>?
)
