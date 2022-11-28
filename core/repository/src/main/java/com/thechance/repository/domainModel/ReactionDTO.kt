package com.thechance.repository.domainModel


import com.google.gson.annotations.SerializedName

data class ReactionDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("last_three_reactions")
    val lastThreeReactions: LastThreeReactions?
)