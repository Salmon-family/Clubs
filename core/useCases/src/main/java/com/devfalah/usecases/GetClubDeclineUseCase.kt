package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubDeclineUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(clubId: Int, memberId: Int, userId: Int): Boolean {
        return clubRepository.declineClub(clubId, memberId, userId)
    }
}