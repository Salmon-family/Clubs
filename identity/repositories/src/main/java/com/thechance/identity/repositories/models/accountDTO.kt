package com.thechance.identity.repositories.models

import com.google.gson.annotations.SerializedName
import com.thechance.identity.repositories.models.IconDto


data class accountDTO(
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: IconDto?,
    @SerializedName("username")
    val username: String?
)