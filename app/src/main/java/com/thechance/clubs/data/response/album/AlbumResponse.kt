package com.thechance.clubs.data.response.album


import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("album")
    val album: AlbumDTO?,
    @SerializedName("list")
    val list: List<PhotoDTO?>?
)