package com.thechance.remote.response.album


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.album.PhotoDTO

data class UserPicture(
    @SerializedName("list")
    val list: List<PhotoDTO?>?
)