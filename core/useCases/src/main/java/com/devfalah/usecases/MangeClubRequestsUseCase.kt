package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class MangeClubRequestsUseCase @Inject constructor(
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