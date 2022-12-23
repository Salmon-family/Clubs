package com.thechance.identity.usecases

import com.thechance.identity.entities.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
    private val saveUserId: SaveUserIdUseCase,
) {

    suspend operator fun invoke(userName: String, password: String): User {
        val user = identityRepository.login(userName, password)
        saveUserId(user.guid.toString())
        return user
    }
}