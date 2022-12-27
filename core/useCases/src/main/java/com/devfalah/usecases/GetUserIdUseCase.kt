package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val repository: ClubRepository
) {
     operator fun invoke(): Int {
        return repository.getUserId()
    }
}