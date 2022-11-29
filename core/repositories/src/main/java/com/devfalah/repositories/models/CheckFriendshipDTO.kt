package com.devfalah.repositories.models


import com.google.gson.annotations.SerializedName

data class CheckFriendshipDTO(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("is_friend")
    val isFriend: Boolean?,
    @SerializedName("request_exists")
    val requestExists: Boolean?
)