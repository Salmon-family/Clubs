package com.devfalah.repositories.models


import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("comments:post")
    val commentsPost: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("subject_guid")
    val subjectGuid: Int?,
    @SerializedName("time_created")
    val timeCreated: Long?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("user")
    val user: UserDTO,
    @SerializedName("total_likes")
    val totalLikes: Int?,
    @SerializedName("is_liked_by_user")
    val isLikedByUser: Boolean?,
)