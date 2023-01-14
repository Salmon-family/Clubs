package com.devfalah.usecases.club

import com.devfalah.entities.User
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetClubMembersUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private var page = 1
    private var count = 0

    suspend operator fun invoke(ownerId: Int, groupID: Int): List<User> {
        val members = clubRepository.getGroupMembers(groupID, page)
        count = members.total
        return if (members.total > 0) {
            page = members.page + 1
            members.friends.filterNot { it.id == ownerId }
        } else {
            emptyList()
        }
    }

    /**
     * (-1) because the owner should not count but api return count with club owner.
     * */
    fun getTotalMembers(): Int {
        return if (count > 0) {
            count - 1
        } else {
            count
        }
    }
}