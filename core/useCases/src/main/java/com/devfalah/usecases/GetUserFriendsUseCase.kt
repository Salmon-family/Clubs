package com.devfalah.usecases

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserFriendsUseCase @Inject constructor(
    private val authRepository: ClubRepository,
) {

    suspend operator fun invoke(userId: Int): List<User> {
        return authRepository.getUserFriends(userId)
    }
}