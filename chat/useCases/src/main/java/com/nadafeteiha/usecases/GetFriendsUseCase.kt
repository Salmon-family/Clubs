package com.nadafeteiha.usecases

import com.thechance.entities.Friends
import javax.inject.Inject

class GetFriendsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    private var page = 1

    suspend operator fun invoke(): Friends {
        val userId = chatRepository.getUserId()
        val friends = chatRepository.getAllFriends(userId, page = page)
        if (friends.friends.isNotEmpty()) {
            page = friends.page + 1
        }
        return friends
    }

}