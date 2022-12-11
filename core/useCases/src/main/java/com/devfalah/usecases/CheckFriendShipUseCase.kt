package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class CheckFriendShipUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userID: Int, profileID: Int): Boolean {
        return try {
            clubRepository.checkFriendShip(userID, profileID)
        } catch (t: Throwable) {
            false
        }
    }
}