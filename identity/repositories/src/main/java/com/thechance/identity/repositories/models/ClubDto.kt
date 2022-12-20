package com.thechance.identity.repositories.models


import com.google.gson.annotations.SerializedName

data class ClubDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("guid")
    val guid: Int? = null,
    @SerializedName("ismember")
    val isMember: Boolean? = null,
    @SerializedName("membership")
    val membership: String? = null,
    @SerializedName("object_guid")
    val objectGuid: Int? = null,
    @SerializedName("owner_guid")
    val ownerGuid: Int? = null,
    @SerializedName("request_exists")
    val requestExists: Boolean? = null,
    @SerializedName("subtype")
    val subtype: String? = null,
    @SerializedName("time_created")
    val timeCreated: Int? = null,
    @SerializedName("time_updated")
    val timeUpdated: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("total_requests")
    val totalRequests: Int? = null,
    @SerializedName("type")
    val type: String? = null
)