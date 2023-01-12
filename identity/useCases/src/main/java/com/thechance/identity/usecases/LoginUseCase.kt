package com.thechance.identity.usecases

import com.thechance.identity.entities.User
import com.thechance.identity.entities.UserData
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
    private val saveUserId: SaveUserIdUseCase,
    private val clearDataBase: ClearDataBaseUseCase,
) {

    suspend operator fun invoke(userName: String, password: String): User {
        val user = identityRepository.login(userName, password)
        clearDataBase(user.guid)
        saveUserId(user.guid.toString())
        identityRepository.saveAuthState()
        updateFcmToken(user, password)
        return user
    }

    private suspend fun updateFcmToken(user: User, password: String){
        val fcmToken = identityRepository.getToken()
        val updatedUser = UserData(
            userId = user.guid,
            newEmail = user.email,
            newGender = user.gender,
            currentPassword = password,
            newFullName = user.fullName,
            newFcmToken = fcmToken,
            newJobTitle = user.jobTitle
        )
        identityRepository.updateFcmToken(updatedUser)
    }

}