package com.devfalah.remote.response


import com.devfalah.repositories.models.album.AlbumDTO
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