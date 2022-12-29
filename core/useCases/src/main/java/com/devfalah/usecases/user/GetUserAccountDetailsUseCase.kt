package com.devfalah.usecases.user

import com.devfalah.entities.FriendShip
import com.devfalah.entities.User
import com.devfalah.usecases.friend.CheckFriendShipUseCase
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserAccountDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val friendShip: CheckFriendShipUseCase
) {

    suspend operator fun invoke(profileOwnerId: Int): User {
        val profileId = if (profileOwnerId == -1) {
            clubRepository.getUserId()
        } else {
            profileOwnerId
        }

        val userDetails = clubRepository.getUserAccountDetails(userID = profileId)
        val userId = clubRepository.getUserId()
        val friendShip = if (userId != profileId) {
            friendShip(userId, profileId)
        } else {
            FriendShip()
        }
        return userDetails.copy(
            isMyProfile = userId == profileId,
            isFriend = friendShip.isFriend,
            isRequestExists = friendShip.requestExists && !friendShip.isFriend
        )
    }
}