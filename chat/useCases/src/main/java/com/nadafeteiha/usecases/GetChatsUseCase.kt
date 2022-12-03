package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(): Flow<List<Chat>> {
        refreshChats()
        return chatRepository.getChatsFromLocal()
    }

    private suspend fun refreshChats() {
        val chats = chatRepository.getChats(10).chats
        chatRepository.insertChatsLocally(chats)
    }
}