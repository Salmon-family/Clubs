package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(stringerID: Int): Boolean {
        return clubRepository.addFriendRequest(
            userID = clubRepository.getUserId(),
            friendRequestID = stringerID
        )
    }
}