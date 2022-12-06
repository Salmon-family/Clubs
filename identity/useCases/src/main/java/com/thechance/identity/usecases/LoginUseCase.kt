package com.thechance.identity.usecases

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {

    suspend operator fun invoke(userName: String, password: String): Boolean {
        return identityRepository.login(userName, password)
    }
}