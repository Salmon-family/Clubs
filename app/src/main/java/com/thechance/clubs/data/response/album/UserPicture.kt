package com.thechance.clubs.data.response.album


import com.google.gson.annotations.SerializedName

data class UserPicture(
    @SerializedName("list")
    val list: List<PhotoDTO?>?
)