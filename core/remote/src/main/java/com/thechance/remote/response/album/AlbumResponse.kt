package com.thechance.remote.response.album


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.PhotoDTO

data class AlbumResponse(
    @SerializedName("album")
    val album: AlbumDetailsDTO?,
    @SerializedName("list")
    val list: List<PhotoDTO?>?
)