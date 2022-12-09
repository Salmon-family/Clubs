package com.nadafeteiha.usecases

import com.thechance.entities.Message
import com.thechance.entities.Notification
import javax.inject.Inject

class SetSendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(userID: Int, friendID: Int, text: String): Message {
        val message = chatRepository.setSendMessage(userID, friendID, text)
       val x = chatRepository.postNotification(
            notification = Notification(
                id = message.id,
                friendId = userID,
                messageText = text,
                time = message.time,
                to = "fqx2x1DxRverG02nPSIHdL:APA91bEpQ-Jm8AkJqsfjXzBF3tX-xWYKCiBQVcI022H7LTxoV4QrkJE9_vkOl2XU18c31lP3nb6yi3tBiA-laY5GUrqbKGh0X9O195hd4D5KL0oZENiaFLXm09RcfNon0XOjOyE3z1h4"
            )
        )
        chatRepository.insertMessage(message)
        return message
    }
}