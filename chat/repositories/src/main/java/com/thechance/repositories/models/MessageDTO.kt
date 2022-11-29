package com.thechance.repositories.models


import com.google.gson.annotations.SerializedName

data class MessageDTO(
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: IconDto?,
    @SerializedName("username")
    val username: String?
)