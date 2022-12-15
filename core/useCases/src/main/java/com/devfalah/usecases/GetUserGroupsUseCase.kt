package com.devfalah.usecases

import com.devfalah.entities.Group
import javax.inject.Inject

class GetUserGroupsUseCase @Inject constructor (
    private val repository: ClubRepository
) {

    suspend operator fun invoke(userId: Int): List<Group> {
        return repository.GetUserGroups(userId)
    }
}