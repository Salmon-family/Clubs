package com.thechance.clubs.data.response.album


import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    @SerializedName("albums")
    val albums: List<AlbumDTO>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("cover_photo")
    val coverPhoto: String?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("profile_photo")
    val profilePhoto: String?
)