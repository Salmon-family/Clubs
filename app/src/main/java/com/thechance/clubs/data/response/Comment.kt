package com.thechance.clubs.data.response


import com.google.gson.annotations.SerializedName

data class Comment(
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