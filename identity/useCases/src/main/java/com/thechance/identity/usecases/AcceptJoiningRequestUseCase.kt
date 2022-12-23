package com.thechance.identity.usecases

import javax.inject.Inject

class AcceptJoiningRequestUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {
    suspend operator fun invoke(clubId: Int, userId: Int, clubOwnerId: Int): Boolean{
        return identityRepository.acceptJoiningRequest(clubId, userId, clubOwnerId)
    }
}