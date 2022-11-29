package com.nadafeteiha.usecases

import com.thechance.entities.Conversation
import javax.inject.Inject

class GetChatWithFriendUseCase @Inject constructor(private val chatRepository: ChatRepository) {

    suspend operator fun invoke(useID: Int, friendID: Int): Conversation {
        return chatRepository.getMessagesWithFriend(useID, friendID)
    }
}