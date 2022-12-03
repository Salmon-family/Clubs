package com.devfalah.usecases

import com.devfalah.entities.User
import javax.inject.Inject

class GetUserAccountDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(userId: Int): User {
        return clubRepository.getUserAccountDetails(userId)
    }
}