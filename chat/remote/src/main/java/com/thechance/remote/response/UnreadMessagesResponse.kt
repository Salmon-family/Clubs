package com.thechance.remote.response

import com.google.gson.annotations.SerializedName
import com.devfalah.repository.models.MessagesDTO
import com.devfalah.repository.models.UserDTO

data class UnreadMessagesResponse(
    @SerializedName("list")
    val list: List<MessagesDTO>?,
    @SerializedName("withuser")
    val withuser: UserDTO?
)