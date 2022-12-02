package com.nadafeteiha.usecases

import javax.inject.Inject

class InsertChatsLocallyUseCase @Inject constructor(
    private val chatRepository: ChatRepository
){
    suspend operator fun invoke(userID: Int) {
        chatRepository.insertChatsLocally(userID)
    }
}