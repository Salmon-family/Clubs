package com.nadafeteiha.usecases

import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatWithFriendUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(friendId: Int): Flow<List<Message>> {
        return chatRepository.getMessages(friendId)
    }

    suspend fun refreshMessages(userID: Int, friendID: Int, page: Int): Int {
        val messages = chatRepository.getMessages(userID, friendID, page)
        chatRepository.insertMessages(messages.messages)
        return messages.count
    }
}