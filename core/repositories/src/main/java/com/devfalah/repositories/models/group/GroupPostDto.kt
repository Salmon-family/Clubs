package com.devfalah.repositories.models.group


import com.devfalah.repositories.models.PostDTO
import com.devfalah.repositories.models.UserDTO
import com.google.gson.annotations.SerializedName

data class GroupPostDto(
    @SerializedName("friends")
    val friends: List<Any>?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("post")
    val post: PostDTO,
    @SerializedName("text")
    val text: String?,
    @SerializedName("user")
    val user: UserDTO
)