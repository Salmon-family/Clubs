package com.devfalah.usecases

import com.devfalah.entities.MyClubs
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserClubsUseCase @Inject constructor (
    private val repository: ClubRepository,
    private val getUserId: GetUserIdUseCase
) {

    suspend operator fun invoke(specialClubsIds: List<Int>): MyClubs {
        val myClubs = repository.getGroupsIDsThatUserMemberOF(getUserId())
        return MyClubs(
            mySpecialClubs = myClubs.filter { specialClubsIds.contains(it.id) },
            myClubs = myClubs.filterNot { specialClubsIds.contains(it.id) }
        )
    }
}