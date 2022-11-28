package com.thechance.remote.response


import com.google.gson.annotations.SerializedName

data class SuccessDTO(
    @SerializedName("success")
    val success: String?
)