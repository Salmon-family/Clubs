package com.nadafeteiha.usecases

import com.thechance.entities.Message
import com.thechance.entities.Notification
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(name:String,friendID: Int, text: String,fcmToken: String): Message {
        val userID = chatRepository.getUserId()
        val message = chatRepository.sendMessage(userID, friendID, text)
        chatRepository.postNotification(
            notification = Notification(
                id = message.id,
                friendId = userID,
                to = fcmToken,
                body = message.message,
                title = name,
                clickAction = "newMessage"
            )
        )
        chatRepository.insertMessage(message)
        chatRepository.updateRecentMessage(message.friendId,message.message)
        return message
    }
}