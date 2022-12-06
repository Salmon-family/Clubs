package com.thechance.identity.repositories.models

import com.google.gson.annotations.SerializedName

data class UserDataDTO(
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("reemail")
    val reEmail: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)