package com.thechance.identity.usecases

import javax.inject.Inject

class SaveStartOnBoardingValueUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {
    suspend operator fun invoke(value: Boolean): Boolean {
        return identityRepository.saveFirstInstallValue(value)
    }
}