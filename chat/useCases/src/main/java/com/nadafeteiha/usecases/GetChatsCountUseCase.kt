package com.nadafeteiha.usecases

import javax.inject.Inject

class GetChatsCountUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(userID: Int): Int {
        return chatRepository.getChatsCount(userID)
    }

    suspend fun getChatsCountLocally(): Int {
        return chatRepository.getChatsCount()
    }
}