package com.devfalah.usecases

import com.devfalah.entities.Friends
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserFriendsUseCase @Inject constructor(
    private val authRepository: ClubRepository,
) {

    suspend operator fun invoke(userId: Int): Friends {
        return authRepository.getUserFriends(userId)
    }
}