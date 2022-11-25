package com.thechance.clubs.data.response


import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("comments")
    val comments: List<Comment?>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?
)