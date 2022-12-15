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
                to = "f9HAVG-GSRKYwb2i61VmPv:APA91bFhH7lx03b85S7ztuJ3rapkm1OS0TFHlA3FR2zb1rJk5cjFWtsiy_YNerP9K4Nl4cDGZuus3mDP2M0yu1QDjU9zvqaU21fNRs6FLfnzQPWUQyjY2wl1-8gSYIlZetK6q731S4eW"
            )
        )
        chatRepository.insertMessage(message)
        chatRepository.updateRecentMessage(message.friendId,message.message)
        return message
    }
}