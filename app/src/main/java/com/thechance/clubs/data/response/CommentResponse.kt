package com.thechance.clubs.data.response


import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("comment")
    val comment: Comment?,
    @SerializedName("user")
    val user: UserDTO?
)