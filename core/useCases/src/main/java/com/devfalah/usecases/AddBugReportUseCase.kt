package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class AddBugReportUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    operator fun invoke(
        userId: Int,
        message: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        return clubRepository.addBugReport(userId, message, onSuccess, onFail)
    }
}