package com.devfalah.usecases.club

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubRequestsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(clubId: Int): List<User> {
        return clubRepository.getRequestsToClub(clubId)
    }
}