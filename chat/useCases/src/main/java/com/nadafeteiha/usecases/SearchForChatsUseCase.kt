package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchForChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    operator fun invoke(query: String) : Flow<List<Chat>> {
        return chatRepository.getChats(query)
    }
}