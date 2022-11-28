package com.thechance.usecase

import com.thechance.entity.User

class GetUserDetailsUseCase(private val repository: Repository) {

    suspend operator fun invoke(): User {
        return repository.getUserDetails() ?: throw Throwable("Error")
    }
}