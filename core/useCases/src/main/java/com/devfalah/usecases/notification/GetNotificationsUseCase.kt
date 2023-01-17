package com.devfalah.usecases.notification

import com.devfalah.entities.Notification
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    private var page = 1
    private val userId = clubRepository.getUserId()

    suspend operator fun invoke(): List<Notification> {
        val notifications = clubRepository.getNotifications(userId, page)
        return if (notifications.isNotEmpty()) {
            page += 1
            notifications
        } else {
            emptyList()
        }
    }
}