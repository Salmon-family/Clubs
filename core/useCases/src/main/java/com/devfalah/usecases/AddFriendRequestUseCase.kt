package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class AddFriendRequestUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userID: Int, friendRequestId: Int): Boolean {
        return clubRepository.addFriendRequest(userID = userID, friendRequestID = friendRequestId)
    }
}