package com.devfalah.usecases

import com.devfalah.entities.User
import com.devfalah.entities.UserInformation
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class EditUserInformationUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(user: UserInformation): User {
        return clubRepository.editUserInformation(user = user)
    }
}