package com.thechance.clubs.data.response.album


import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("album")
    val album: AlbumDetailsDTO?,
    @SerializedName("list")
    val list: List<PhotoDTO?>?
)