package com.thechance.identity.remote.response

import com.google.gson.annotations.SerializedName

data class IdentityBaseResponse<T>(
    @SerializedName("code")
    val code: String?,
    @SerializedName("merchant")
    val merchant: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("payload")
    val payload: T?,
    @SerializedName("time_token")
    val timeToken: Int?,
    @SerializedName("url")
    val url: String?
)