package com.devfalah.usecases.club

import com.devfalah.entities.Club
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class CreateClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository) {
    suspend operator fun invoke(groupName: String, description: String, groupPrivacy: Int): Club {
        return clubRepository.createClub(
            userID = clubRepository.getUserId(),
            groupName = groupName,
            description = description,
            groupPrivacy = groupPrivacy,
        )
    }
}