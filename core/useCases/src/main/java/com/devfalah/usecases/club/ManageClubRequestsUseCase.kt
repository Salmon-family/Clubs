package com.devfalah.usecases.club

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class ManageClubRequestsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend fun acceptRequest(ownerId: Int, memberId: Int, clubId: Int): Boolean {
        return clubRepository
            .acceptClubRequest(userId = ownerId, memberId = memberId, clubId = clubId)
    }

    suspend fun declineRequest(ownerId: Int, memberId: Int, clubId: Int): Boolean {
        return clubRepository
            .acceptClubRequest(userId = ownerId, memberId = memberId, clubId = clubId)

    }
}