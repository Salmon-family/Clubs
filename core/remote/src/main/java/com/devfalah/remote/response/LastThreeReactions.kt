package com.devfalah.remote.response


import com.google.gson.annotations.SerializedName

data class LastThreeReactions(
    @SerializedName("like")
    val like: String?
)