package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
    private val repository: ClubRepository
) {

    suspend operator fun invoke(userId: Int) {
        repository.saveUserId(userId)
    }
}