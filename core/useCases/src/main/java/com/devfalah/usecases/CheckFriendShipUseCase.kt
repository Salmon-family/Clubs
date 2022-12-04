package com.devfalah.usecases

import javax.inject.Inject

class CheckFriendShipUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userID: Int, profileID: Int): Boolean {
        return clubRepository.checkFriendShip(userID,profileID)
    }
}