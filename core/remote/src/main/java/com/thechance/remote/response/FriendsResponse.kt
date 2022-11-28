package com.thechance.remote.response


import com.google.gson.annotations.SerializedName

data class FriendsResponse(
    @SerializedName("friends")
    val list: List<com.thechance.remote.response.FriendDTO>?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("total")
    val total: Int?
)