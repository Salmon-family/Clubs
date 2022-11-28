package com.devfalah.repositories.models

import com.google.gson.annotations.SerializedName

data class Icon(
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
