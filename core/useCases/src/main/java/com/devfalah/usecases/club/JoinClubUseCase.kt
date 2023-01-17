package com.devfalah.usecases.club

import com.devfalah.entities.Club
import com.devfalah.entities.NotificationRequest
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class JoinClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val manageClubRequestsUseCase: ManageClubRequestsUseCase
) {
    suspend operator fun invoke(clubId: Int, ownerId: Int): Club {
        val memberId = clubRepository.getUserId()
        val club = clubRepository.joinClub(clubId, memberId)
        if (isSpecialClub(clubId)) {
            manageClubRequestsUseCase.acceptRequest(OWNER_ID, memberId, clubId)
        } else {
            sendJoinClubNotification(club.name, ownerId, memberId)
        }
        return club
    }

    private fun isSpecialClub(clubId: Int): Boolean {
        return clubId in 67..78
    }

    private suspend fun sendJoinClubNotification(clubName: String, ownerId: Int, memberId: Int) {
        try {
            val ownerDetails = clubRepository.getUserAccountDetails(ownerId)
            val memberDetails = clubRepository.getUserAccountDetails(memberId)
            clubRepository.pushNotification(
                NotificationRequest(
                    title = clubName,
                    body = memberDetails.name,
                    to = ownerDetails.token,
                    clickAction = "joinClubRequest"
                )
            )
        } catch (throwable: Throwable) {
            println("testNotification ${throwable.message}")
        }

    }

    companion object {
        private const val OWNER_ID = 16
    }
}