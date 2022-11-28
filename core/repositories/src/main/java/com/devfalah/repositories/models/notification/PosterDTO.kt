package com.devfalah.repositories.models.notification


import com.google.gson.annotations.SerializedName

data class PosterDTO(
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: String?
)