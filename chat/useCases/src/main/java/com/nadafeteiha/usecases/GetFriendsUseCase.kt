package com.nadafeteiha.usecases

import com.thechance.entities.Friend
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFriendsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke(): Flow<List<Friend>> {
        return chatRepository.getFriends()
    }

    suspend fun getFriends(page: Int): Int {
        val friends = chatRepository.getAllFriends(chatRepository.getUserId(), page)
        chatRepository.insertFriends(friends.friends)
        return friends.count
    }

    suspend fun loadingMoreFriends(
        friendsCount: Int,
        friendsCountLocally: Int,
    ): Boolean {
        return if (friendsCount > friendsCountLocally) {
            val nextPage = friendsCountLocally / 10 + 1
            getFriends(nextPage)
            false
        } else {
            true
        }
    }
}