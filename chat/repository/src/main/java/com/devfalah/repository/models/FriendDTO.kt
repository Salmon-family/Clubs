package com.devfalah.repository.models


import com.google.gson.annotations.SerializedName

data class FriendDTO(
    @SerializedName("birthdate")
    val birthdate: String? = null,
    @SerializedName("cover_url")
    val coverUrl: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("fcm_token")
    val fcmToken: String? = null,
    @SerializedName("fullname")
    val fullname: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("guid")
    val guid: Int? = null,
    @SerializedName("icon")
    val icon: IconDto? = null,
    @SerializedName("job_title")
    val jobTitle: String? = null,
    @SerializedName("language")
    val language: Any? = null,
    @SerializedName("username")
    val username: String? = null
)