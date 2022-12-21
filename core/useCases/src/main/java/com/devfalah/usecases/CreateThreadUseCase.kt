package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class CreateThreadUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userId: Int, postContent: String, privacy: Int): Post {
        return clubRepository.publishPostUserWall(userId, userId, postContent, privacy)
    }
}