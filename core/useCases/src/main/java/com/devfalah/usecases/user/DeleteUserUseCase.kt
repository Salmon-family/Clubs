package com.devfalah.usecases.user

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: ClubRepository) {

    suspend operator fun invoke() {
        return repository.deleteUserId()
    }
}