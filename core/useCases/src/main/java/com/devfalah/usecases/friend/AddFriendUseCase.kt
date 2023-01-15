package com.devfalah.usecases.friend

import com.devfalah.entities.NotificationRequest
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(stringerID: Int, token: String): Boolean {
        val userId = clubRepository.getUserId()

        val isRequestSuccess = clubRepository.addFriendRequest(
            userID = userId,
            friendRequestID = stringerID
        )

        if (isRequestSuccess){
            sendNotification(userId, stringerID, token)
        }

        return isRequestSuccess
    }


    private suspend fun sendNotification(userId: Int, friendId: Int, token: String) {
        val userDetails = clubRepository.getUserAccountDetails(userId)
        clubRepository.pushNotification(
            NotificationRequest(
                id = userId,
                friendId = friendId,
                to = token,
                title = "friend request",
                body = userDetails.name,
                clickAction = "friendRequest"
            )
        )
    }
}