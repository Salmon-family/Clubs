package com.nadafeteiha.usecases

import com.thechance.entities.Message
import javax.inject.Inject

class SetSendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
    ) {

    suspend operator fun invoke(userID: Int, friendID: Int, message: String): Message {
        val message =  chatRepository.setSendMessage(userID, friendID, message)
        chatRepository.insertMessage(message)
        return message
    }
}