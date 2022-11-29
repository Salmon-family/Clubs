package com.thechance.remote.response

import com.google.gson.annotations.SerializedName
import com.thechance.repositories.models.MessagesDTO
import com.thechance.repositories.models.UserDTO

data class UnreadMessagesResponse(
    @SerializedName("list")
    val list: List<MessagesDTO>?,
    @SerializedName("withuser")
    val withuser: UserDTO?
)