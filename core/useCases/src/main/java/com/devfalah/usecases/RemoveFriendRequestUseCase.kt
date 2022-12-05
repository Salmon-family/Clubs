package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class RemoveFriendRequestUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userID: Int, friendRequestId:Int): Boolean {
        return clubRepository.removeFriendRequest(userID = userID, friendRequestID = friendRequestId)
    }
}