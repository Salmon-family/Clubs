package com.devfalah.usecases.user

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class CheckIfLoggedInUseCase @Inject constructor(
    private val repository: ClubRepository
) {
     operator fun invoke(): Boolean {
        return repository.isUserLoggedIn()
    }
}