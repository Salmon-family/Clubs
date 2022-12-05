package com.devfalah.repository.models


import com.google.gson.annotations.SerializedName

data class ConversationDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<ChatDTO>?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("withuser")
    val withUser: UserDTO?
)