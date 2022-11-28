package com.devfalah.remote.response


import com.devfalah.repositories.models.CommentDto
import com.devfalah.repositories.models.UserDTO
import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("comment")
    val comment: CommentDto?,
    @SerializedName("user")
    val user: UserDTO?
)