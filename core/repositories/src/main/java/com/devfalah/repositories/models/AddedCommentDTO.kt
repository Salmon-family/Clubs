package com.devfalah.repositories.models


import com.google.gson.annotations.SerializedName

data class AddedCommentDTO(
    @SerializedName("comment")
    val comment: CommentDto?,
    @SerializedName("user")
    val user: UserDTO?
)