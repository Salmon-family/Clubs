package com.devfalah.usecases

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserAccountDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val friendShip: CheckFriendShipUseCase
) {

    suspend operator fun invoke(userId: Int, profileOwnerId: Int): User {
        val userDetails = clubRepository.getUserAccountDetails(userID = profileOwnerId)
        val isFriends = if (userId != profileOwnerId) {
            friendShip(userId, profileOwnerId)
        } else {
            false
        }
        return userDetails.copy(isFriend = isFriends)
    }
}