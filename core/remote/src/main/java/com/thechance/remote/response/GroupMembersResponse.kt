package com.thechance.remote.response


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.UserDTO

data class GroupMembersResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("members")
    val members: List<UserDTO?>?,
    @SerializedName("offset")
    val offset: Int?
)