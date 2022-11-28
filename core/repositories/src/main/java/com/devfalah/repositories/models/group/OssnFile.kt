package com.devfalah.repositories.models.group


import com.google.gson.annotations.SerializedName

data class OssnFile(
    @SerializedName("active")
    val active: Int?,
    @SerializedName("count")
    val count: Boolean?,
    @SerializedName("entity_types")
    val entityTypes: List<String>?,
    @SerializedName("filetype")
    val filetype: String?,
    @SerializedName("limit")
    val limit: Boolean?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("order_by")
    val orderBy: String?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("page_limit")
    val pageLimit: Int?,
    @SerializedName("permission")
    val permission: Int?,
    @SerializedName("subtype")
    val subtype: String?,
    @SerializedName("time_created")
    val timeCreated: Int?,
    @SerializedName("time_updated")
    val timeUpdated: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("types")
    val types: Types?
)