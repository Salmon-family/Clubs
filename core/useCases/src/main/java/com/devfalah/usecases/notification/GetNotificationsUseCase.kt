package com.devfalah.usecases.notification

import com.devfalah.entities.Notification
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(): List<Notification> {
        return clubRepository.getNotifications(clubRepository.getUserId())
    }

}