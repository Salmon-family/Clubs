package com.devfalah.usecases.postDetailsUseCases

import com.devfalah.entities.Comment
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetAllCommentsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(userId: Int, postId: Int): List<Comment> {
        return clubRepository.getAllComments(userId, postId)
    }
}
