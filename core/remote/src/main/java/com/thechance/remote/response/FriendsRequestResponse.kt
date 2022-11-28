package com.thechance.remote.response

import com.google.gson.annotations.SerializedName

data class FriendsRequestResponse(
    @SerializedName("requests")
    val list: List<com.thechance.remote.response.FriendDTO>?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("total")
    val total: Int?
)