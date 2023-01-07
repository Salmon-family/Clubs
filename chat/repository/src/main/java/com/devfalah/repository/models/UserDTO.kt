package com.devfalah.repository.models

import com.google.gson.annotations.SerializedName


data class UserDTO(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fcm_token")
    val fcmToken: String?,
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: IconDto?,
    @SerializedName("job_title")
    val jobTitle: String?,
    @SerializedName("username")
    val username: String?
)