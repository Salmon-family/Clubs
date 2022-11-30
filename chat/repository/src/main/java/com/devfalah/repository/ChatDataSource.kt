package com.devfalah.repository

import com.devfalah.repository.models.ConversationDTO

interface ChatDataSource {

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): ConversationDTO
}