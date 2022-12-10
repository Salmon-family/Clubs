package com.thechance.identity.repositories.models

import com.google.gson.annotations.SerializedName

data class UserDataDTO(
    @SerializedName("firstname")
    var firstname: String,
    @SerializedName("lastname")
    var lastname: String,
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