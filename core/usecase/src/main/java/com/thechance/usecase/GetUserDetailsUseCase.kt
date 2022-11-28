package com.thechance.usecase

import com.thechance.entity.User

class GetUserDetailsUseCase(private val repository: Repository) {

    suspend operator fun invoke(userID: Int): User {
        return repository.getUserDetails(userID) ?: throw Throwable("Error")
    }
}