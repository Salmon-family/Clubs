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
    val timeCreated: Int?,
    @SerializedName("type")
    val type: String?
)