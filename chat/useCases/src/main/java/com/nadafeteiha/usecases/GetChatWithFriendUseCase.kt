package com.nadafeteiha.usecases

import com.thechance.entities.Conversation
import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatWithFriendUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke(useID: Int, friendID: Int): Conversation {
        return chatRepository.getMessagesWithFriend(useID, friendID)
    }

    suspend fun refreshMessages(userID: Int, friendID: Int) {
        val message = chatRepository.getMessagesWithFriend(userID, friendID).messages
        chatRepository.insertMessagesLocally(message)
    }

    fun getMessagesFromLocal(): Flow<List<Message>> {
        return chatRepository.getMessagesFromLocal()
    }
}