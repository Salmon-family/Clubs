package com.thechance.remote.response.album


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.PhotoDTO
import com.thechance.repository.domainModel.UserDTO

data class PhotoDetailsWithUserResponse(
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("photo")
    val photo: PhotoDTO?,
    @SerializedName("user")
    val user: UserDTO?
)