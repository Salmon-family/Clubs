package com.devfalah.repositories.models.message


import com.devfalah.repositories.models.IconDto
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