package com.devfalah.remote.response


import com.devfalah.repositories.models.album.PhotoDTO
import com.google.gson.annotations.SerializedName

data class UserPicture(
    @SerializedName("list")
    val list: List<PhotoDTO?>?
)