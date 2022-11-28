package com.thechance.remote.response.album


import com.google.gson.annotations.SerializedName

data class AlbumDTO(
    @SerializedName("album")
    val album: com.thechance.remote.response.album.AlbumDetailsDTO?,
    @SerializedName("image_url")
    val imageUrl: String?
)