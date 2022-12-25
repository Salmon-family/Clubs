package com.devfalah.usecases

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubMembersUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(groupID: Int): List<User> {
        val members = clubRepository.getGroupMembers(groupID)
        println(members)
        return members
    }
}