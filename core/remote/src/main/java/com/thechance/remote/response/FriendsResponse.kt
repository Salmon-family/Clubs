package com.thechance.remote.response


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.FriendDTO

data class FriendsResponse(
    @SerializedName("friends")
    val list: List<FriendDTO>?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("total")
    val total: Int?
)