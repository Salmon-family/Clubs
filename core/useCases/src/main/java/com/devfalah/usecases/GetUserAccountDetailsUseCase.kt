package com.devfalah.usecases

import com.devfalah.entities.FriendShip
import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserAccountDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val friendShip: CheckFriendShipUseCase
) {

    suspend operator fun invoke(userId: Int, profileOwnerId: Int): User {
        val userDetails = clubRepository.getUserAccountDetails(userID = profileOwnerId)
        val friendShip = if (userId != profileOwnerId) {
            friendShip(userId, profileOwnerId)
        } else {
            FriendShip()
        }
        return userDetails.copy(
            isMyProfile = userId == profileOwnerId,
            isFriend = friendShip.isFriend,
            isRequestExists = friendShip.requestExists && !friendShip.isFriend
        )
    }
}