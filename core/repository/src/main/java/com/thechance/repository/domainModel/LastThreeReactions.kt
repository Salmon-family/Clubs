package com.thechance.repository.domainModel


import com.google.gson.annotations.SerializedName

data class LastThreeReactions(
    @SerializedName("like")
    val like: String?
)