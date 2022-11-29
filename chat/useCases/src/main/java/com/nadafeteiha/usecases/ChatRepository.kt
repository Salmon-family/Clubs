package com.nadafeteiha.usecases

import com.thechance.entities.Conversation

interface ChatRepository {

    suspend fun getMessagesWithFriend(userID: Int, friendID: Int): Conversation
}