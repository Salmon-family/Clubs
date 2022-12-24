package com.thechance.identity.usecases

import javax.inject.Inject

class GetFcmTokenUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {
    operator fun invoke(): String{
        return identityRepository.getFcmToken()
    }
}