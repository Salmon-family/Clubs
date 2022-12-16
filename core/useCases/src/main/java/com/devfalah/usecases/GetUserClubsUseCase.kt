package com.devfalah.usecases

import com.devfalah.entities.Club
import javax.inject.Inject

class GetUserClubsUseCase @Inject constructor (
    private val repository: ClubRepository
) {

    suspend operator fun invoke(userId: Int): List<Club> {
        return repository.GetUserGroups(userId)
    }
}