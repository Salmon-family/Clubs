package com.thechance.identity.usecases

import com.thechance.identity.entities.UserData
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {

    suspend operator fun invoke(userData: UserData): String {
        val user = userData.copy(fcmToken = identityRepository.getToken())
        return identityRepository.signup(user).substringAfter(delimiter = ":").trim()
    }
}