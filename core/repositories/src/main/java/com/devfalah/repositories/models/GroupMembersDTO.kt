package com.devfalah.repositories.models


import com.google.gson.annotations.SerializedName

data class GroupMembersDTO(
    @SerializedName("count")
    val total: Int?,
    @SerializedName("members")
    val members: List<UserDTO>?,
    @SerializedName("offset")
    val offset: Int?



)