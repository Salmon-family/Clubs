package com.devfalah.usecases.friend

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserFriendRequestsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(): List<User> {
        return clubRepository.getUserFriendRequests(userID = clubRepository.getUserId()).distinctBy { it.id }
    }
}