package com.thechance.clubs.data.response


import com.google.gson.annotations.SerializedName

data class FriendsResponse(
    @SerializedName("friends")
    val list: List<FriendDTO>?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("total")
    val total: Int?
)