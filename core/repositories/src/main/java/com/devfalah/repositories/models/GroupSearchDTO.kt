package com.devfalah.repositories.models

import com.google.gson.annotations.SerializedName

data class GroupSearchDTO(
    @SerializedName("guid")
    val groupId: Int?,
    @SerializedName("time_created")
    val timeCreated: Int?,
    @SerializedName("owner_guid")
    val ownerId: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("subtype")
    val subtype: String?,
    @SerializedName("membership")
    val membership: String?
)
