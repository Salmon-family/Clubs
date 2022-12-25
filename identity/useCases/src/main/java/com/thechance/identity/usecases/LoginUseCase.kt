package com.thechance.identity.usecases

import com.thechance.identity.entities.User
import com.thechance.identity.entities.UserData
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
    private val getTokenUseCase: GetTokenUseCase,
    private val saveUserId: SaveUserIdUseCase,
) {

    suspend operator fun invoke(userName: String, password: String): User {
        val user = identityRepository.login(userName, password)
        saveUserId(user.guid.toString())
        updateFcmToken(user, password)
        return user
    }

    private suspend fun updateFcmToken(user: User, password: String){
        val updatedUser = UserData(
            userId = user.guid,
            newEmail = user.email,
            newGender = user.gender,
            currentPassword = password,
            newFullName = user.fullName,
            newFcmToken = getFcmToken(),
            newJobTitle = user.jobTitle
        )
        identityRepository.updateFcmToken(updatedUser)
    }

    private suspend fun getFcmToken(): String{
        return identityRepository.getToken()
    }
}