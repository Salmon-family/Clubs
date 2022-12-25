package com.thechance.identity.usecases

import com.thechance.identity.entities.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
    private val getTokenUseCase: GetTokenUseCase,
) {

    suspend operator fun invoke(userName: String, password: String): User {
        println("DEVFALAH ${getTokenUseCase()}")
        return identityRepository.login(userName, password)
    }
}