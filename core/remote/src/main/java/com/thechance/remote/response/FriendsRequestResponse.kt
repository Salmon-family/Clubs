package com.thechance.remote.response

import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.FriendDTO

data class FriendsRequestResponse(
    @SerializedName("requests")
    val list: List<FriendDTO>?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("total")
    val total: Int?
)