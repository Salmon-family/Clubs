package com.thechance.identity.usecases

import javax.inject.Inject

class SetStartInstallUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {

    suspend operator fun invoke(value: Boolean) {
        identityRepository.setStartInstallState(value)
    }
}