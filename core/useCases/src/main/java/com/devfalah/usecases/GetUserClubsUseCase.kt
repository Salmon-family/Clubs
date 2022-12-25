package com.devfalah.usecases

import com.devfalah.entities.Club
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserClubsUseCase @Inject constructor(
    private val repository: ClubRepository
) {

    suspend operator fun invoke(userId: Int): List<Club> {
        return repository.getGroupsIDsThatUserMemberOF(userId)
    }
}