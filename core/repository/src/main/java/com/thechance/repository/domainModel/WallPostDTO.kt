package com.thechance.repository.domainModel


import com.google.gson.annotations.SerializedName

data class WallPostDTO(
    @SerializedName("friends")
    val friends: List<Any>?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("post")
    val post: PostDTO?,
    @SerializedName("posted_user")
    val postedUser: UserDTO?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("user")
    val user: UserDTO?
)