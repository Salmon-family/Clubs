package com.nadafeteiha.usecases

import com.thechance.entities.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatWithFriendUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(userId: Int, friendId: Int): Flow<List<Message>> {
        refreshMessages(userId, friendId)
        return chatRepository.getMessages(friendId)
    }

    private suspend fun refreshMessages(userID: Int, friendID: Int){
        val message = chatRepository.getMessages(userID, friendID)
        chatRepository.insertMessages(message)
    }
}