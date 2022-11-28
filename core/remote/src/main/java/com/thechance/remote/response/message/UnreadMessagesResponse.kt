package com.thechance.remote.response.message


import com.google.gson.annotations.SerializedName
import com.thechance.repository.domainModel.UserDTO

data class UnreadMessagesResponse(
    @SerializedName("list")
    val list: List<MessagesDTO>?,
    @SerializedName("withuser")
    val withuser: UserDTO?
)