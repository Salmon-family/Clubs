package com.thechance.identity.repositories.models

import com.google.gson.annotations.SerializedName


data class AccountDTO(
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: IconDto?,
    @SerializedName("username")
    val username: String?
)