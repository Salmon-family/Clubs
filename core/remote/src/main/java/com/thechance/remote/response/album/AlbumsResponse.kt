package com.thechance.remote.response.album


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.album.AlbumDTO

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