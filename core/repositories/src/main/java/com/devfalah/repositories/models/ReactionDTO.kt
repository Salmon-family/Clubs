package com.devfalah.repositories.models


import com.google.gson.annotations.SerializedName

data class ReactionDTO(
    @SerializedName("count")
    val count: Int?
)