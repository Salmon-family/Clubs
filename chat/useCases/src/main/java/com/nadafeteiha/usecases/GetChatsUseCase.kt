package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(userID: Int): Flow<List<Chat>> {
        refreshChats(userID, 1)
        return chatRepository.getChats()
    }

    suspend fun refreshChats(userID: Int, page: Int) {
        val chats = chatRepository.getChats(userID, page = page)
        chatRepository.insertChats(chats)
    }
}