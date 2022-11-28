package com.devfalah.remote.response


import com.devfalah.repositories.models.UserDTO
import com.devfalah.repositories.models.album.PhotoDTO
import com.google.gson.annotations.SerializedName

data class PhotoDetailsWithUserResponse(
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("photo")
    val photo: PhotoDTO?,
    @SerializedName("user")
    val user: UserDTO?
)