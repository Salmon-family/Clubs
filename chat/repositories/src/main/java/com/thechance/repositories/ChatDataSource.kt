package com.thechance.repositories

import com.thechance.repositories.models.ConversationDTO

interface ChatDataSource {

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO
}