package com.thechance.repositories.models


import com.google.gson.annotations.SerializedName

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