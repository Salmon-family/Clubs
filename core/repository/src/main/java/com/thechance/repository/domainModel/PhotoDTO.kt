package com.thechance.repository.domainModel


import com.google.gson.annotations.SerializedName

data class PhotoDTO(
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("is_liked_by_user")
    val isLikedByUser: Boolean?,
    @SerializedName("time_created")
    val timeCreated: Int?,
    @SerializedName("total_likes")
    val totalLikes: Int?
)