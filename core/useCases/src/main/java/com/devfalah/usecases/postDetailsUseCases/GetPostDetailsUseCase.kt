package com.devfalah.usecases.postDetailsUseCases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(userId: Int, postId: Int): Post {
        return clubRepository.getPostDetails(userId, postId)
    }
}