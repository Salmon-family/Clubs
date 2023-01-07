package com.thechance.identity.usecases

import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
) {
    suspend operator fun invoke(): String {
        return identityRepository.getToken()
    }
}