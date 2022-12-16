package com.thechance.identity.usecases

import com.thechance.identity.entities.Club
import javax.inject.Inject

class JoinClubUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {
    suspend operator fun invoke(clubId: Int, userId: Int): Club{
        return identityRepository.joinClub(clubId, userId)
    }
}