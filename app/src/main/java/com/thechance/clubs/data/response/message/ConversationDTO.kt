package com.thechance.clubs.data.response.message


import com.google.gson.annotations.SerializedName
import com.thechance.clubs.data.response.UserDTO

data class ConversationDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<MessagesDTO>?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("withuser")
    val withuser: UserDTO?
)