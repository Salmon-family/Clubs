package com.nadafeteiha.usecases

import com.thechance.entities.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val getUserId: GetUserIdUseCase,
) {
    suspend operator fun invoke(): Flow<List<Chat>> {
        return chatRepository.getChats()
    }

    suspend fun getChats(page: Int): Int {
        val chats = chatRepository.getChats(getUserId(), page)
        chatRepository.insertChats(chats.chats)
        return chats.count
    }

    suspend fun loadingMoreChats(
        chatsCount: Int,
        chatsCountLocally: Int,
    ): Boolean {
        return if (chatsCount > chatsCountLocally) {
            val nextPage = chatsCountLocally / 10 + 1
            getChats(nextPage)
            false
        } else {
            true
        }
    }
}