package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend  operator fun invoke(): Flow<List<Chat>> {
        return chatRepository.getChats()
    }

    suspend fun refreshChats(userID: Int,page: Int): Int{
        val chats = chatRepository.getChats(userID,page)
        chatRepository.insertChats(chats.chats)
        return chats.count
    }
}