package com.thechance.clubs.data.response.album


import com.google.gson.annotations.SerializedName
import com.thechance.clubs.data.response.UserDTO

data class PhotoDetailsWithUserResponse(
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("photo")
    val photo: PhotoDTO?,
    @SerializedName("user")
    val user: UserDTO?
)