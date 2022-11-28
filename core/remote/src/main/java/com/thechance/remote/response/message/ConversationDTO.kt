package com.thechance.remote.response.message


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.UserDTO

data class ConversationDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<com.thechance.remote.response.message.MessagesDTO>?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("withuser")
    val withuser: UserDTO?
)