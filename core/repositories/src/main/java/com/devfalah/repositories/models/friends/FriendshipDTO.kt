package com.devfalah.repositories.models.friends


import com.google.gson.annotations.SerializedName

data class FriendshipDTO(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("is_friend")
    val isFriend: Boolean?,
    @SerializedName("request_exists")
    val requestExists: Boolean?
)