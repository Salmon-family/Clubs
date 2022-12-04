package com.thechance.remote.response

import com.google.gson.annotations.SerializedName
import com.devfalah.repository.models.ChatDTO
import com.devfalah.repository.models.UserDTO

data class UnreadMessagesResponse(
    @SerializedName("list")
    val list: List<ChatDTO>?,
    @SerializedName("withuser")
    val withUser: UserDTO?
)