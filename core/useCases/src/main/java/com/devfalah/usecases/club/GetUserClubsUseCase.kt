package com.devfalah.usecases.club

import com.devfalah.entities.MyClubs
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetUserClubsUseCase @Inject constructor (
    private val repository: ClubRepository,
) {

    suspend operator fun invoke(specialClubsIds: List<Int>): MyClubs {
        val myClubs = repository.getGroupsIDsThatUserMemberOF(repository.getUserId())
        return MyClubs(
            mySpecialClubs = myClubs.filter { specialClubsIds.contains(it.id) },
            myClubs = myClubs.filterNot { specialClubsIds.contains(it.id) }
        )
    }
}