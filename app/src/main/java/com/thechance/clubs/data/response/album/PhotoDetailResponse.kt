package com.thechance.clubs.data.response.album


import com.google.gson.annotations.SerializedName

data class PhotoDetailResponse(
    @SerializedName("album")
    val album: AlbumDetailsDTO?,
    @SerializedName("photo")
    val photo: PhotoDTO?
)