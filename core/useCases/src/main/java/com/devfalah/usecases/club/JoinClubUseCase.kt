package com.devfalah.usecases.club

import com.devfalah.entities.Club
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class JoinClubUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val manageClubRequestsUseCase: ManageClubRequestsUseCase
) {
    suspend operator fun invoke(clubId: Int): Club {
        val memberId = clubRepository.getUserId()
        val club = clubRepository.joinClub(clubId, memberId)
        if(isSpecialClub(clubId)){
            manageClubRequestsUseCase.acceptRequest(OWNER_ID, memberId, clubId)
        }
        return club
    }

    private fun isSpecialClub(clubId: Int): Boolean{
        return clubId in 67..78
    }

    companion object{
        private const val OWNER_ID = 16
    }
}