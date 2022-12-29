package com.devfalah.usecases.friend

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class RemoveFriendRequestUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(friendRequestId: Int): Boolean {
        return clubRepository.removeFriendRequest(
            userID = clubRepository.getUserId(),
            friendRequestID = friendRequestId
        )
    }
}