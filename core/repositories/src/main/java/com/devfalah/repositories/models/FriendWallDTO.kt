package com.devfalah.repositories.models

import com.devfalah.repositories.models.post.PostDTO
import com.google.gson.annotations.SerializedName

data class FriendWallDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("posts")
    val posts: List<PostDTO>?,
    @SerializedName("user")
    val user: UserDTO?,
    @SerializedName("viewer_is_friend")
    val viewerIsFriend: Boolean?
)