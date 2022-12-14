package com.devfalah.usecases.user

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import java.io.File
import javax.inject.Inject

class ChangeProfileImageUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userId: Int, file: File): User {
        return clubRepository.addProfilePicture(userId, file)
    }
}