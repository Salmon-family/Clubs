package com.devfalah.usecases.friend

import com.devfalah.entities.Friends
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserFriendsUseCase @Inject constructor(
    private val authRepository: ClubRepository,
) {
    private var page = 1

    suspend operator fun invoke(profileUserID: Int): Friends {
        val profileId = if (profileUserID == -1) { authRepository.getUserId() }
        else { profileUserID }

        val friends = authRepository.getUserFriends(profileId, page = page)
        if (friends.friends.isNotEmpty()) {
            page = friends.page + 1
        }
        return friends
    }
}