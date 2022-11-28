package com.thechance.repository.domainModel.album


import com.google.gson.annotations.SerializedName

data class AlbumDTO(
    @SerializedName("album")
    val album: AlbumDetailsDTO?,
    @SerializedName("image_url")
    val imageUrl: String?
)