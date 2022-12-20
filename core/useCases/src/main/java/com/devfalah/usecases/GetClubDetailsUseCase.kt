package com.devfalah.usecases

import com.devfalah.entities.Club
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val getUserId: GetUserIdUseCase,
) {
    suspend operator fun invoke(userID: Int, groupID: Int): Club {
        return clubRepository.getGroupDetails(userID = userID, groupID = groupID)
    }
}