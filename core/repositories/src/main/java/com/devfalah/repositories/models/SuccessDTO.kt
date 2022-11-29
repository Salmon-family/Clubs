package com.devfalah.repositories.models


import com.google.gson.annotations.SerializedName

data class SuccessDTO(
    @SerializedName("success")
    val success: String?
)