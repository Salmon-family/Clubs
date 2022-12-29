package com.devfalah.usecases.friend

import com.devfalah.entities.FriendShip
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class CheckFriendShipUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userID: Int, profileID: Int): FriendShip {
        try {
            return clubRepository.checkFriendShip(userID, profileID)
        } catch (t: Throwable) {
            throw Throwable(t.message.toString())
        }
    }
}