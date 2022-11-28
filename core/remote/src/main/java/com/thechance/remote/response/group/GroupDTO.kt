package com.thechance.remote.response.group


import com.google.gson.annotations.SerializedName

data class GroupDTO(
    @SerializedName("description")
    val description: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("membership")
    val membership: String?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("subtype")
    val subtype: String?,
    @SerializedName("time_created")
    val timeCreated: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("coverurl")
    val coverurl: Boolean?,
    @SerializedName("ismember")
    val ismember: Boolean?,
    @SerializedName("OssnFile")
    val ossnFile: com.thechance.remote.response.group.OssnFile?,
    @SerializedName("request_exists")
    val requestExists: Boolean?,
    @SerializedName("total_requests")
    val totalRequests: Int?,

    )