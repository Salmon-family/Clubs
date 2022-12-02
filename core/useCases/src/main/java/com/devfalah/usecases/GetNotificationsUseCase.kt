package com.devfalah.usecases

import com.devfalah.entities.Notifications
import com.devfalah.entities.User
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(userId: Int): List<Notifications> {
        return clubRepository.getNotifications(userId)
    }

}