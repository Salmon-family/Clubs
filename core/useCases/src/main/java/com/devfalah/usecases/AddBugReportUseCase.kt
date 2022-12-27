package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class AddBugReportUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(
        message: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        return clubRepository.addBugReport(clubRepository.getUserId(), message, onSuccess, onFail)
    }
}