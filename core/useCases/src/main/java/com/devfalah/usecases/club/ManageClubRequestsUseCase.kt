package com.devfalah.usecases.club

import com.devfalah.entities.NotificationRequest
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class ManageClubRequestsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend fun acceptRequest(
        ownerId: Int,
        memberId: Int,
        clubId: Int,
        memberToken: String = ""
    ): Boolean {
        val acceptClubRequestResult =
            clubRepository.acceptClubRequest(userId = ownerId, memberId = memberId, clubId = clubId)

        if (acceptClubRequestResult && memberToken.isNotEmpty()) {
            sendAcceptJoinClubRequestNotification(ownerId, clubId, memberToken)
        }

        return acceptClubRequestResult
    }

    private suspend fun sendAcceptJoinClubRequestNotification(
        userId: Int,
        clubId: Int,
        memberToken: String
    ) {
        val clubDetails = clubRepository.getGroupDetails(userID = userId, groupID = clubId)
        clubRepository.pushNotification(
            NotificationRequest(
                body = clubDetails.name,
                to = memberToken,
                clickAction = "acceptJoinClubRequest"
            )
        )
    }

    suspend fun declineRequest(ownerId: Int, memberId: Int, clubId: Int): Boolean {
        return clubRepository
            .declineClubRequest(userId = ownerId, memberId = memberId, clubId = clubId)

    }
}