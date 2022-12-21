package com.devfalah.usecases

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import java.io.File
import javax.inject.Inject

class ChangeProfileImageUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val analyzeImage: AnalyzeImageUseCase,
) {
    suspend operator fun invoke(userId: Int, file: File): User {
        analyzeImage(file)
        return clubRepository.addProfilePicture(userId, file)
    }
}