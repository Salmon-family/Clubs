package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class MarkNotificationAsViewedUseCase @Inject constructor(
    private val repository: ClubRepository
) {
    suspend operator fun invoke(notificationId: Int) {
        repository.markNotificationAsViewed(notificationId)
    }
}