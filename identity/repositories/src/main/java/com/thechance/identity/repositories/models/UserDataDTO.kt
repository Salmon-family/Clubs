package com.thechance.identity.repositories.models

import com.google.gson.annotations.SerializedName

data class UserDataDTO(
    @SerializedName("fullname")
    val fullName: String,
    @SerializedName("job_title")
    val jobTitle: String,
    @SerializedName("fcm_token")
    val fcmToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("reemail")
    val reemail: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("guid")
    val userId: Int = 0,
    @SerializedName("new_email")
    val newEmail: String = "",
    @SerializedName("new_gender")
    val newGender: String = "",
    @SerializedName("current_password")
    val currentPassword: String = "",
    @SerializedName("new_full_name")
    val newFullName: String = "",
    @SerializedName("new_fcm_token")
    val newFcmToken: String = "",
    @SerializedName("new_job_title")
    val newJobTitle: String = ""
)