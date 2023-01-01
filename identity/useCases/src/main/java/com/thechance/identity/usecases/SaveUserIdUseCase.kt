package com.thechance.identity.usecases

import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {
    suspend operator fun invoke(id: String){
        identityRepository.saveUserId(id.toInt())
    }
}