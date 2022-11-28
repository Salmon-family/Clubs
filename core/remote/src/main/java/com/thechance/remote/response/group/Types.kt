package com.thechance.remote.response.group


import com.google.gson.annotations.SerializedName

data class Types(
    @SerializedName("annotation")
    val `annotation`: String?,
    @SerializedName("component")
    val component: String?,
    @SerializedName("entity")
    val entity: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("object")
    val objectX: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("user")
    val user: String?
)