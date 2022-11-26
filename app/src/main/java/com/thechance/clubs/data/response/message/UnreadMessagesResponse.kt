package com.thechance.clubs.data.response.message


import com.google.gson.annotations.SerializedName
import com.thechance.clubs.data.response.UserDTO

data class UnreadMessagesResponse(
    @SerializedName("list")
    val list: List<MessagesDTO>?,
    @SerializedName("withuser")
    val withuser: UserDTO?
)