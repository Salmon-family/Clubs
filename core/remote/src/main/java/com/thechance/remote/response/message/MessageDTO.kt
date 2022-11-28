package com.thechance.remote.response.message


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.Icon

data class MessageDTO(
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: Icon?,
    @SerializedName("username")
    val username: String?
)