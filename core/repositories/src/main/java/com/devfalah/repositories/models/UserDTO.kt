package com.devfalah.repositories.models

import com.google.gson.annotations.SerializedName


data class UserDTO(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("cover_url")
    val coverUrl: Boolean?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: IconDto?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("username")
    val username: String?
)