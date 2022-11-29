package com.devfalah.remote.response


import com.devfalah.repositories.models.album.AlbumDetailsDTO
import com.devfalah.repositories.models.album.PhotoDTO
import com.google.gson.annotations.SerializedName

data class PhotoDetailResponse(
    @SerializedName("album")
    val album: AlbumDetailsDTO?,
    @SerializedName("photo")
    val photo: PhotoDTO?
)