package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class EditClubUseCase @Inject constructor(
    private val repository: ClubRepository,
    private val getUserId: GetUserIdUseCase
){
    suspend operator fun invoke(
        clubId: Int, clubName: String, description: String, clubPrivacy: Int,
    ): Boolean {
        return repository.editClub(clubId, getUserId(), clubName, description, clubPrivacy)
    }
}