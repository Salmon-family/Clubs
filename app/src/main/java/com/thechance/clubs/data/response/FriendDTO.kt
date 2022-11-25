package com.thechance.clubs.data.response


import com.google.gson.annotations.SerializedName

data class FriendDTO(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("cover_url")
    val coverUrl: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: Icon?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("username")
    val username: String?
)