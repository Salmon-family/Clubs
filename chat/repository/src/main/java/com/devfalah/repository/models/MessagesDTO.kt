package com.devfalah.repository.models


import com.google.gson.annotations.SerializedName

data class MessagesDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_deleted_from")
    val isDeletedFrom: String?,
    @SerializedName("is_deleted_to")
    val isDeletedTo: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("message_from")
    val messageFrom: MessageDTO?,
    @SerializedName("message_to")
    val messageTo: MessageDTO?,
    @SerializedName("time")
    val time: Long?,
    @SerializedName("viewed")
    val viewed: String?
)