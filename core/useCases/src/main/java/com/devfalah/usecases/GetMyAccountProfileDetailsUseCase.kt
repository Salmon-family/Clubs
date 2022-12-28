package com.devfalah.usecases

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetMyAccountProfileDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(): User {
        return clubRepository.getUserAccountDetails(userID = clubRepository.getUserId())
    }
}