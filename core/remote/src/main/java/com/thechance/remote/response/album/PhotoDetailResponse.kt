package com.thechance.remote.response.album


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.album.PhotoDTO
import com.thechance.repository.domainModel.album.AlbumDetailsDTO

data class PhotoDetailResponse(
    @SerializedName("album")
    val album: AlbumDetailsDTO?,
    @SerializedName("photo")
    val photo: PhotoDTO?
)