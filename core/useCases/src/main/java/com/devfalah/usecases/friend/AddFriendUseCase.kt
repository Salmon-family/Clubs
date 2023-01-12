package com.devfalah.usecases.friend

import com.devfalah.entities.NotificationRequest
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(stringerID: Int, token: String): Boolean {
        val userId = clubRepository.getUserId()
        clubRepository.pushNotification(
            NotificationRequest(
                id = userId,
                friendId = stringerID,
                to = token,
                title = "friend request",
                body = "someone send you friend request",
                clickAction = "friendRequest"
            )
        )
        return clubRepository.addFriendRequest(
            userID = userId,
            friendRequestID = stringerID
        )
    }
}