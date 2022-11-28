package com.thechance.remote.response


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.PostDTO
import com.thechance.repository.domainModel.UserDTO

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