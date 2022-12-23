package com.thechance.identity.repositories.models

import com.google.gson.annotations.SerializedName

data class UserDataDTO(
    @SerializedName("fullname")
    var fullName: String,
    @SerializedName("job_title")
    var jobTitle: String,
    @SerializedName("fcm_token")
    val fcmToken: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("reemail")
    var reemail: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("birthdate")
    var birthdate: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String
)