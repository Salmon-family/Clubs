package com.thechance.clubs.data.response


import com.google.gson.annotations.SerializedName

data class SuccessDTO(
    @SerializedName("success")
    val success: String?
)