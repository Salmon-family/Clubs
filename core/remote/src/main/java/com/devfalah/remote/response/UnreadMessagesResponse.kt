package com.devfalah.remote.response


import com.devfalah.repositories.models.UserDTO
import com.devfalah.repositories.models.message.MessagesDTO
import com.google.gson.annotations.SerializedName

data class UnreadMessagesResponse(
    @SerializedName("list")
    val list: List<MessagesDTO>?,
    @SerializedName("withuser")
    val withuser: UserDTO?
)