package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubMembersUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(groupID: Int): Int {
        return clubRepository.getGroupMembers(groupID)
    }
}