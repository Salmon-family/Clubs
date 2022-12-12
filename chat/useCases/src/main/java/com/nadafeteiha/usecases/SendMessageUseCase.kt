package com.nadafeteiha.usecases

import com.thechance.entities.Message
import com.thechance.entities.Notification
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(userID: Int, friendID: Int, text: String): Message {
        val message = chatRepository.sendMessage(userID, friendID, text)
        chatRepository.postNotification(
            notification = Notification(
                id = message.id,
                friendId = userID,
                messageText = text,
                time = message.time.toString(),
                to = "cD0K3w_EQ4ufkFhufn0p4C:APA91bH94OfXT508XpMJvg_uZTX8uK8p8Cx0J_6zr2ZiwzXTXxnC1hLz7KRlUE4kX_o7nCpK-TUeq80Siu6oq1K-R3VuCGLrNqC2tjouAldPbAgWxCPn6PifipcpSbYdsSjNZuYSw4wC"
            )
        )
        chatRepository.insertMessage(message)
        chatRepository.updateRecentMessage(message.friendId,message.message)
        return message
    }
}