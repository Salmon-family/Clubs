package com.devfalah.repositories.models

import com.google.gson.annotations.SerializedName


data class UserDTO(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("cover_url")
    val coverUrl: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fcm_token")
    val fcmToken: String?,
    @SerializedName("job_title")
    val jobTitle: String?,
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