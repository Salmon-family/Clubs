package com.thechance.identity.repositories.models

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("cover_url")
    val coverUrl: Boolean?,
    @SerializedName("email")
    val email: String?,
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
    @SerializedName("job_title")
    val jobTitle: String?,
    @SerializedName("fcm_token")
    val fcmToken: String,
    @SerializedName("username")
    val username: String?
)