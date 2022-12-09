package com.devfalah.usecases

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserFriendRequestsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userID: Int): List<User> {
        return clubRepository.getUserFriendRequests(userID = userID).distinctBy { it.id }
    }
}