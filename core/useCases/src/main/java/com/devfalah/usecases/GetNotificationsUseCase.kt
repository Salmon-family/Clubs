package com.devfalah.usecases

import com.devfalah.entities.Notification
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val getUserId: GetUserIdUseCase
) {

    suspend operator fun invoke(): List<Notification> {
        return clubRepository.getNotifications(getUserId())
    }

}