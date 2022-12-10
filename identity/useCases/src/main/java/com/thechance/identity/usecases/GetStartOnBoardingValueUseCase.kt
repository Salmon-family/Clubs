package com.thechance.identity.usecases

import javax.inject.Inject

class GetStartOnBoardingValueUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {

    suspend operator fun invoke(): Boolean {
        return identityRepository.getFirstInstallValue()
    }
}