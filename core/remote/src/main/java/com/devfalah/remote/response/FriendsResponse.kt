package com.devfalah.remote.response


import com.devfalah.repositories.models.FriendDTO
import com.google.gson.annotations.SerializedName

data class FriendsResponse(
    @SerializedName("friends")
    val list: List<FriendDTO>?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("total")
    val total: Int?
)