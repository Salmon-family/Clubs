package com.thechance.repository.domainModel


import com.google.gson.annotations.SerializedName

data class SuccessDTO(
    @SerializedName("success")
    val success: String?
)