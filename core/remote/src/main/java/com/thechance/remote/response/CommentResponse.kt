package com.thechance.remote.response


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.UserDTO

data class CommentResponse(
    @SerializedName("comment")
    val comment: Comment?,
    @SerializedName("user")
    val user: UserDTO?
)