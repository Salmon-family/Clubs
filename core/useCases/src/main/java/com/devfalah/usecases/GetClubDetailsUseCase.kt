package com.devfalah.usecases

import com.devfalah.entities.Club
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(groupID: Int): Club {
        val userID = clubRepository.getUserId()
        val club = clubRepository.getGroupDetails(userID = userID, groupID = groupID)
        return club.copy(isOwner = userID == club.ownerId)
    }
}