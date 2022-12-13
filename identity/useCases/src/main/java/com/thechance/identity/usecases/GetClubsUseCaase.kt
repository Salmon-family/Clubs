package com.thechance.identity.usecases

import com.thechance.identity.entities.Club
import javax.inject.Inject

class GetClubsUseCaase @Inject constructor(
    private val identityRepository: IdentityRepository
) {
    operator fun invoke(): List<Club>{
        return identityRepository.getClubs()
    }
}