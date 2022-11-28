package com.thechance.repository.domainModel


import com.google.gson.annotations.SerializedName

data class PostDTO(
    @SerializedName("access")
    val access: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("is_liked_by_user")
    val isLikedByUser: Boolean?,
    @SerializedName("item_guid")
    val itemGuid: String?,
    @SerializedName("item_type")
    val itemType: String?,
    @SerializedName("last_three_reactions")
    val lastThreeReactions: String?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("poster_guid")
    val posterGuid: String?,
    @SerializedName("subtype")
    val subtype: String?,
    @SerializedName("time_created")
    val timeCreated: Int?,
    @SerializedName("time_updated")
    val timeUpdated: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("total_comments")
    val totalComments: Int?,
    @SerializedName("total_likes")
    val totalLikes: Int?,
    @SerializedName("type")
    val type: String?
)