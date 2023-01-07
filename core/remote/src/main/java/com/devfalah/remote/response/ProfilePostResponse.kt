package com.devfalah.remote.response


import com.devfalah.repositories.models.UserDTO
import com.devfalah.repositories.models.post.WallPostDTO
import com.google.gson.annotations.SerializedName

data class ProfilePostResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("posts")
    val posts: List<WallPostDTO>?,
    @SerializedName("user")
    val user: UserDTO?,
    @SerializedName("viewer_is_friend")
    val viewerIsFriend: Boolean?
)