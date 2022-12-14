package com.thechance.identity.usecases

import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {
    suspend operator fun invoke(): Int{
        return identityRepository.getUserId()
    }
}