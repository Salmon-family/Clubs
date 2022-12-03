package com.nadafeteiha.usecases

import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMessagesFromLocalUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    operator fun invoke(): Flow<List<Message>> {
        return chatRepository.getMessagesFromLocal()
    }
}