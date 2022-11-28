package com.thechance.remote.response.notification


import com.google.gson.annotations.SerializedName

data class NotificationDTO(
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("item_guid")
    val itemGuid: Int?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("poster_guid")
    val posterGuid: Int?,
    @SerializedName("subject_guid")
    val subjectGuid: Int?,
    @SerializedName("time_created")
    val timeCreated: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("viewed")
    val viewed: String?
)