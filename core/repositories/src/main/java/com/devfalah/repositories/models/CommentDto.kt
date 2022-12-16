package com.devfalah.repositories.models


import com.devfalah.entities.User
import com.google.gson.annotations.SerializedName
import java.io.File

data class CommentDto(
    @SerializedName("comments:post")
    val commentsPost: String?,
    @SerializedName("id")
    val id: Int?,
//    @SerializedName("file:comment:photo")
//    val fileCommentPhoto: File?,
    @SerializedName("is_liked_by_user")
    val isLikedByUser: Boolean?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("subject_guid")
    val subjectGuid: Int?,
    @SerializedName("time_created")
    val timeCreated: Long?,
    @SerializedName("total_likes")
    val totalLikes: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("user")
    val user: UserDTO?
)