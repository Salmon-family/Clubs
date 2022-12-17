package com.devfalah.repositories.models


import com.devfalah.entities.User
import com.devfalah.repositories.models.group.GroupDTO
import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("groups")
    val groups: List<GroupDTO>?,
    @SerializedName("users")
    val users: List<UserDTO>?
)