package com.thechance.identity.usecases

import com.thechance.identity.entities.Club
import javax.inject.Inject

class JoinClubUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
    private val acceptJoiningRequestUseCase: AcceptJoiningRequestUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) {

    suspend operator fun invoke(selectedClubs: List<Club>){
        val userId = getUserIdUseCase()?.toInt() ?: 0
        selectedClubs.forEach {  club ->
            identityRepository.joinClub(club.id, userId)
            acceptJoiningRequestUseCase(club.id, userId, OWNER_ID)
        }
    }

    companion object{
        private const val OWNER_ID = 16
    }
}