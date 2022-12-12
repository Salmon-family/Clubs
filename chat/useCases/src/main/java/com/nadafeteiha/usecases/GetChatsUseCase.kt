package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(userID: Int): Flow<List<Chat>> {
        refreshChats(userID)
        return chatRepository.getChats()
    }

    private suspend fun refreshChats(userID: Int) {
        val chats = chatRepository.getChats(userID)
        chatRepository.insertChats(chats)
    }
}