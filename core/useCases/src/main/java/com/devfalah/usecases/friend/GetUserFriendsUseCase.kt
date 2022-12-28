package com.devfalah.usecases.friend

import com.devfalah.entities.Friends
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserFriendsUseCase @Inject constructor(
    private val authRepository: ClubRepository,
) {
    private var page = 1

    suspend operator fun invoke(userId: Int): Friends {
        val friends = authRepository.getUserFriends(userId, page = page)
        if (friends.friends.isNotEmpty()) {
            page = friends.page + 1
        }
        return friends
    }
}