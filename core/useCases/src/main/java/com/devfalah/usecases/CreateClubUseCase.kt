package com.devfalah.usecases

import com.devfalah.entities.Club
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class CreateClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val getUserId: GetUserIdUseCase,
) {
    suspend operator fun invoke(groupName: String, description: String, groupPrivacy: Int): Club {
        return clubRepository.createClub(
            userID = getUserId(),
            groupName = groupName,
            description = description,
            groupPrivacy = groupPrivacy,
        )
    }
}