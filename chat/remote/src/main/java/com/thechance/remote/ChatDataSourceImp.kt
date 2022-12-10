package com.thechance.remote

import com.devfalah.repository.ChatRemoteDataSource
import com.devfalah.repository.models.ChatDTO
import com.devfalah.repository.models.ConversationDTO
import com.devfalah.repository.models.NotificationDto
import com.thechance.remote.api.ChatService
import com.thechance.remote.api.CloudMessagingService
import com.thechance.remote.response.BaseResponse
import retrofit2.Response
import javax.inject.Inject

class ChatDataSourceImp @Inject constructor(
    private val chatService: ChatService,
    private val cloudMessagingService: CloudMessagingService,
) : ChatRemoteDataSource {

    override suspend fun getChats(userID: Int): ConversationDTO {
        return wrap { chatService.getRecentMessages(userID) }
    }

    override suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO {
        return wrap { chatService.getConversationWithFriend(userID, friendID) }
    }

    override suspend fun sendMessage(from: Int, to: Int, message: String): ChatDTO {
        return wrap { chatService.sendMessage(from, to, message) }
    }

    override suspend fun postNotification(notification: NotificationDto): Boolean {
        return cloudMessagingService.postNotification(notification).isSuccessful


    }


    private suspend fun <T> wrap(
        function: suspend () -> Response<BaseResponse<T>>,
    ): T {
        val response = function()
        return if (response.isSuccessful) {
            when (response.body()?.code) {
                "100" -> response.body()?.payload
                "103" -> response.body()?.message.toString()
                else -> throw Throwable("One or more component required for this request can not be found on remote server")
            } as T
        } else {
            throw Throwable(" Not Success Request ")
        }
    }
}
