package com.devfalah.repositories.models.friends


import com.google.gson.annotations.SerializedName

data class FriendsDTO(
    @SerializedName("friends")
    val list: List<FriendDTO>?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("total")
    val total: Int?
)