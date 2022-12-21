package com.devfalah.usecases

import com.devfalah.entities.GroupWall
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetGroupWallUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userID: Int, groupID: Int): GroupWall {
        return clubRepository.getGroupWallList(userID = userID, groupID = groupID)
    }
}