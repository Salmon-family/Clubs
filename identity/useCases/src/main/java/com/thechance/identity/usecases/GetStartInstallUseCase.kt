package com.thechance.identity.usecases

import javax.inject.Inject

class GetStartInstallUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {

    operator fun invoke(): Boolean?{
        return identityRepository.getStartInstallState()
    }
}