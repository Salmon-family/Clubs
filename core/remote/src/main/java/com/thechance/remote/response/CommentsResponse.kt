package com.thechance.remote.response


import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("comments")
    val comments: List<com.thechance.remote.response.Comment?>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?
)