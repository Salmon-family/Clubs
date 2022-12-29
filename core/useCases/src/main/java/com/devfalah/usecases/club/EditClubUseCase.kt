package com.devfalah.usecases.club

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class EditClubUseCase @Inject constructor(
    private val repository: ClubRepository,
){
    suspend operator fun invoke(
        clubId: Int, clubName: String, description: String, clubPrivacy: Int,
    ): Boolean {
        return repository.editClub(clubId, repository.getUserId(), clubName, description, clubPrivacy)
    }
}