package com.thechance.clubs.data.response

import com.google.gson.annotations.SerializedName

data class FriendsRequestResponse(
    @SerializedName("requests")
    val list: List<FriendDTO>?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("total")
    val total: Int?
)