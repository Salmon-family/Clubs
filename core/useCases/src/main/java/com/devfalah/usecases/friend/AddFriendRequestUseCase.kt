package com.devfalah.usecases.friend

import com.devfalah.entities.NotificationRequest
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class AddFriendRequestUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(friendRequestId: Int, friendToken: String): Boolean {
        val userId = clubRepository.getUserId()
        return if (!clubRepository.addFriendRequest(userID = userId, friendRequestID = friendRequestId)) {
            throw Throwable("Error while accept friend")
        } else {
            sendAcceptFriendRequestNotification(userId, friendToken)
            true
        }
    }

    private suspend fun sendAcceptFriendRequestNotification(userId: Int, friendToken: String) {
        val userDetails = clubRepository.getUserAccountDetails(userId)
        clubRepository.pushNotification(
            NotificationRequest(
                body = userDetails.name,
                to = friendToken,
                clickAction = "acceptFriendRequest"
            )
        )
    }
}