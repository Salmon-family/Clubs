package com.devfalah.usecases.club

import com.devfalah.entities.Club
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class JoinClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(clubId: Int): Club {
        return clubRepository.joinClub(clubId, clubRepository.getUserId())
    }
}