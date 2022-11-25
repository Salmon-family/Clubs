package com.thechance.clubs.data.response


import com.google.gson.annotations.SerializedName

data class LastThreeReactions(
    @SerializedName("like")
    val like: String?
)