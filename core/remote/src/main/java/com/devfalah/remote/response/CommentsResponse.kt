package com.devfalah.remote.response


import com.devfalah.repositories.models.CommentDto
import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("comments")
    val comments: List<CommentDto>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?
)