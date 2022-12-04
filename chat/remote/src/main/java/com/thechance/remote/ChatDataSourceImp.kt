package com.thechance.remote

import com.devfalah.repository.ChatDataSource
import com.devfalah.repository.models.ChatDTO
import javax.inject.Inject

class ChatDataSourceImp @Inject constructor(
    private val service: ChatService,
) : ChatDataSource {

    override suspend fun getChats(userID: Int): List<ChatDTO> {
        return service.getRecentMessages(userID = userID).body()?.payload?.list
            ?: throw Throwable("Error")
    }
}