package com.thechance.identity.usecases

import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
){
    suspend operator fun invoke(){
        identityRepository.getToken()
    }
}