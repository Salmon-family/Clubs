package com.thechance.repository.domainModel.album


import com.google.gson.annotations.SerializedName

data class AlbumDetailsDTO(
    @SerializedName("access")
    val access: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("file:ossn:aphoto")
    val fileOssnAphoto: String?,
    @SerializedName("guid")
    val guid: Int?,
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
    @SerializedName("item_guid")
    val itemGuid: String?,
    @SerializedName("item_type")
    val itemType: String?,
    @SerializedName("poster_guid")
    val posterGuid: String?,
    @SerializedName("time_updated")
    val timeUpdated: String?,

)