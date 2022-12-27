package com.devfalah.usecases

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubMembersUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    var page = 1

    suspend operator fun invoke(ownerId: Int, groupID: Int): List<User> {
        val members = clubRepository.getGroupMembers(groupID, page)
        return if (members.isNotEmpty()) {
            page += 1
            members.filterNot { it.id == ownerId }
        } else if (page == 1) {
            throw Throwable("error")
        } else {
            emptyList()
        }
    }
}