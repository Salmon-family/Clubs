package com.devfalah.usecases.club

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubDeclineUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(clubId: Int, ownerId: Int): Boolean {
        return clubRepository.declineClub(
            clubId,
            memberId = clubRepository.getUserId(),
            userId = ownerId
        )
    }
}