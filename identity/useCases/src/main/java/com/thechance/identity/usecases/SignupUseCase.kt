package com.thechance.identity.usecases

import com.thechance.identity.entities.UserData
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {

    suspend operator fun invoke(userData: UserData): String {
        return identityRepository.signup(userData).substringAfter(delimiter = ":").trim()
    }
}