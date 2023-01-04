package com.thechance.identity.usecases

import com.thechance.identity.entities.User
import com.thechance.identity.entities.UserData
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
    private val getTokenUseCase: GetTokenUseCase,
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
        val updatedUser = UserData(
            userId = user.guid,
            newEmail = user.email,
            newGender = user.gender,
            currentPassword = password,
            newFullName = user.fullName,
            newFcmToken = identityRepository.getToken(),
            newJobTitle = user.jobTitle
        )
        identityRepository.updateFcmToken(updatedUser)
    }

}