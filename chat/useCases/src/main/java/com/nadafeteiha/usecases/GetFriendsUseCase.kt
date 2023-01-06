package com.nadafeteiha.usecases

import com.thechance.entities.Friend
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetFriendsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val getChatsUseCase: GetChatsUseCase,
) {
    suspend operator fun invoke(): List<Friend> {
        val friendsList = chatRepository.getAllFriends(chatRepository.getUserId())
        val chatsList = getChatsUseCase().first().map { it.guid }

        return friendsList.filterNot { chatsList.contains(it.id) }
    }

}