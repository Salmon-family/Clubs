package com.thechance.repositories.models

import com.google.gson.annotations.SerializedName

data class IconDto(
    @SerializedName("large")
    val large: String?,
    @SerializedName("larger")
    val larger: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("smaller")
    val smaller: String?,
    @SerializedName("topbar")
    val topbar: String?
)
