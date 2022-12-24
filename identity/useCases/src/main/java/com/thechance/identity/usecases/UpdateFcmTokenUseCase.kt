package com.thechance.identity.usecases

import com.thechance.identity.entities.User
import com.thechance.identity.entities.UserData
import javax.inject.Inject

class UpdateFcmTokenUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
){
    suspend operator fun invoke(userData: UserData): User{
        return identityRepository.updateFcmToken(userData)
    }
}