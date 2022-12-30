package com.thechance.identity.usecases

import com.thechance.identity.entities.UserData
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
    private val clearDataBase: ClearDataBaseUseCase,
) {

    suspend operator fun invoke(userData: UserData): String {
        val user = userData.copy(fcmToken = identityRepository.getToken())
        clearDataBase(-1)
        return identityRepository.signup(user).substringAfter(delimiter = ":").trim()
    }
}